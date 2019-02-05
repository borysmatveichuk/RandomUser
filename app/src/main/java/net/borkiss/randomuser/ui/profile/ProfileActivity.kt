package net.borkiss.randomuser.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_profile.*
import net.borkiss.randomuser.R
import net.borkiss.randomuser.data.model.User

fun goToProfile(context: Context, user: User) {
    context.startActivity(
            ProfileActivity.newIntent(context, user)
    )
}

class ProfileActivity : AppCompatActivity() {

    companion object {

        private val EXTRA_USER = "ProfileActivity.User"

        fun newIntent(context: Context, user: User): Intent {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra(EXTRA_USER, user)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val user = intent.getParcelableExtra(EXTRA_USER) as User

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupToolbar(getString(R.string.fullName, user.firstName, user.lastName))

        Glide.with(this)
                .load(user.largePicture)
                .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(200)))
                .into(icon)
        val container = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (container == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, ProfileFragment.newInstance(user))
                    .commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar(title: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

}
