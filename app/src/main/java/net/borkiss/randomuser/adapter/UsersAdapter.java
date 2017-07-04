package net.borkiss.randomuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import net.borkiss.randomuser.ProfileActivity;
import net.borkiss.randomuser.R;
import net.borkiss.randomuser.data.model.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {

    private static final String TAG = UsersAdapter.class.getSimpleName();
    private List<User> users;
    private Context context;

    public UsersAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public UsersAdapter.UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersAdapter.UserHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void update(List<User> users) {
        this.users.clear();
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView icon;
        private final TextView title;
        private final TextView city;
        private User user;

        UserHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.imgIcon);
            title = (TextView) itemView.findViewById(R.id.txtTitle);
            city = (TextView) itemView.findViewById(R.id.txtCity);
            itemView.setOnClickListener(this);
        }

        void bind(User user) {
            this.user = user;
            final String name = user.getFirstName() +
                    (user.getLastName() != null && user.getLastName().length() > 0
                            ? " " + user.getLastName() : "");
            title.setText(name);
            city.setText(user.getCity());
            Glide.with(context)
                    .load(user.getMediumPicture())
                    .asBitmap().centerCrop()
                    .placeholder(R.mipmap.ic_icon)
                    .into(new BitmapImageViewTarget(icon) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            icon.setImageDrawable(circularBitmapDrawable);
                        }
                    });

        }

        @Override
        public void onClick(View v) {

            Intent i = ProfileActivity.newIntent(context, user);
            context.startActivity(i);
        }
    }
}
