package net.borkiss.randomuser.data;

import android.support.annotation.NonNull;

import net.borkiss.randomuser.data.local.LocalUserDataSource;
import net.borkiss.randomuser.data.model.User;
import net.borkiss.randomuser.data.remote.RemoteUserDataSource;

import java.util.List;

public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;

    private final UserDataSource remoteUserDataSource;
    private final UserDataSource localUserDataSource;

    private boolean dataIsDirty = false;

    private UserRepository() {
        remoteUserDataSource = RemoteUserDataSource.getInstance();
        localUserDataSource = LocalUserDataSource.getInstance();
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }

        return INSTANCE;
    }



    @Override
    public void getAllUsers(@NonNull final LoadUsersCallback callback) {
        remoteUserDataSource.getAllUsers(new LoadUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                callback.onUsersLoaded(users);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getUser(GetUserCallback callback) {

    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void deleteAllUsers() {

    }
}
