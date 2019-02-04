package net.borkiss.randomuser

import com.google.android.material.snackbar.Snackbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar

import net.borkiss.randomuser.adapter.UsersAdapter
import net.borkiss.randomuser.data.UserDataSource
import net.borkiss.randomuser.data.UserRepository
import net.borkiss.randomuser.data.model.User

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var userRepository: UserRepository? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: UsersAdapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userRepository = UserRepository.INSTANCE
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        setupToolbar()

        val layoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = layoutManager
        adapter = UsersAdapter(users = ArrayList(),
                onClick = { user -> goToProfile(this, user)})
        recyclerView!!.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView!!.context,
                DividerItemDecoration.VERTICAL)
        recyclerView!!.addItemDecoration(dividerItemDecoration)

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout!!.setOnRefreshListener {
            swipeRefreshLayout!!.isRefreshing = true
            loadUsers()
        }

        loadUsers()
    }

    private fun loadUsers() {
        userRepository!!.getAllUsers(object : UserDataSource.LoadUsersCallback {
            override fun onUsersLoaded(users: List<User>) {
                adapter!!.update(users)
                swipeRefreshLayout!!.isRefreshing = false
            }

            override fun onDataNotAvailable() {
                swipeRefreshLayout!!.isRefreshing = false
                showNoDateMessage()
            }
        })
    }

    private fun setupToolbar() {
        if (supportActionBar != null) {
            supportActionBar!!.title = resources.getString(R.string.app_name)
        }
    }

    private fun showNoDateMessage() {
        Snackbar.make(recyclerView!!, R.string.noDataInfo, Snackbar.LENGTH_SHORT).show()
    }
}
