package net.borkiss.randomuser.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.borkiss.randomuser.R
import net.borkiss.randomuser.data.model.User
import net.borkiss.randomuser.data.model.ViewState
import net.borkiss.randomuser.ext.gone
import net.borkiss.randomuser.ext.setVisibleGone
import net.borkiss.randomuser.ext.visible
import net.borkiss.randomuser.ui.profile.goToProfile
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        setupToolbar()

        adapter = UsersAdapter(users = mutableListOf(),
                onClick = { user -> goToProfile(this, user) })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        swipeRefreshLayout.setOnRefreshListener {
            loadUsers(true)
        }

        viewModel.users.observe(this, Observer {
            setViewState(it)
        })

        loadUsers(false)
    }

    private fun setViewState(viewState: ViewState<List<User>>) {
        setupRefresh(false)
        when (viewState) {
            is ViewState.Loading -> {
                recyclerView.gone()
                noData.gone()
                error.gone()
                progress.visible()
            }
            is ViewState.Data -> {
                adapter.update(viewState.data)
                recyclerView.setVisibleGone(viewState.data.isNotEmpty())
                noData.setVisibleGone(viewState.data.isEmpty())
                error.gone()
                progress.gone()
            }
            is ViewState.Error -> {
                recyclerView.gone()
                noData.gone()
                progress.gone()
                error.visible()
                error.text = getString(
                        R.string.errorInformation,
                        viewState.error.localizedMessage)
            }
        }
    }

    private fun loadUsers(isRefresh: Boolean) {
        setupRefresh(isRefresh)
        viewModel.loadUsers()
    }

    private fun setupRefresh(show: Boolean) {
        swipeRefreshLayout.isRefreshing = show
    }

    private fun setupToolbar() {
        if (supportActionBar != null) {
            supportActionBar!!.title = resources.getString(R.string.app_name)
        }
    }
}
