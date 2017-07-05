package net.borkiss.randomuser.data;

import android.support.annotation.NonNull;

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

    void getUser(long userId, GetUserCallback callback);

    long saveUser(User user);

    void deleteAllUsers();

    void refreshUsers();

}
