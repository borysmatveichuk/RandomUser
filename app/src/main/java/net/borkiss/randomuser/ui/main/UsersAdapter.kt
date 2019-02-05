package net.borkiss.randomuser.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.user_item.view.*
import net.borkiss.randomuser.R
import net.borkiss.randomuser.data.model.User

class UsersAdapter(
        private val users: MutableList<User>,
        private val onClick: (User) -> Unit
        ) : RecyclerView.Adapter<UsersAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)

        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun update(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.txtTitle.text = itemView.context.getString(R.string.fullName, user.firstName, user.lastName)
            itemView.txtCity.text = user.city
            Glide.with(itemView.context)
                    .load(user.mediumPicture)
                    .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(64)))
                    .into(itemView.imgIcon)

            itemView.setOnClickListener{ onClick(user) }
        }
    }

}
