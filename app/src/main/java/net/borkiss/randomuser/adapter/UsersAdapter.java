package net.borkiss.randomuser.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import net.borkiss.randomuser.data.model.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = UsersAdapter.class.getSimpleName();
    private List<User> users;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private boolean isClicked = false;

        public UserHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            //prevent double event fire
            if (isClicked) {
                isClicked = false;
                return;
            }

            Log.d(TAG, "Click");
        }
    }
}
