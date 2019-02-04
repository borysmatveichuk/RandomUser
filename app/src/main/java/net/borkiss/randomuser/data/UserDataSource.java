package net.borkiss.randomuser.data;

import androidx.annotation.NonNull;

import net.borkiss.randomuser.data.model.User;

import java.util.List;

public interface UserDataSource {

    interface LoadUsersCallback {
        void onUsersLoaded(List<User> users);
        void onDataNotAvailable();
    }

    interface GetUserCallback {
        void onUserLoaded(User user);
        void onDataNotAvailable();
    }

    void getAllUsers(@NonNull LoadUsersCallback callback);

}
