package net.borkiss.randomuser.data;

import android.content.Context;
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

    private UserRepository(Context context) {
        remoteUserDataSource = RemoteUserDataSource.getInstance();
        localUserDataSource = LocalUserDataSource.getInstance(context);
    }

    public static UserRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(context.getApplicationContext());
        }
        return INSTANCE;
    }



    @Override
    public void getAllUsers(@NonNull final LoadUsersCallback callback) {

        if (dataIsDirty) {
            getFromRemoteDataSource(callback);
            return;
        }

        localUserDataSource.getAllUsers(new LoadUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                callback.onUsersLoaded(users);
            }

            @Override
            public void onDataNotAvailable() {
                getFromRemoteDataSource(callback);
            }
        });
    }

    private void getFromRemoteDataSource(final LoadUsersCallback callback) {
        remoteUserDataSource.getAllUsers(new LoadUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                refreshLocalDataSource(users);
                callback.onUsersLoaded(users);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshLocalDataSource(List<User> users) {
        localUserDataSource.deleteAllUsers();
        for (User user : users) {
            //save ID in object here instead requesting User from DB
            long id = localUserDataSource.saveUser(user);
            user.setId(id);
        }
        dataIsDirty = false;
    }

    @Override
    public void getUser(long userId, final GetUserCallback callback) {
        localUserDataSource.getUser(userId, new GetUserCallback() {
            @Override
            public void onUserLoaded(User user) {
                callback.onUserLoaded(user);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public long saveUser(User user) {
        return localUserDataSource.saveUser(user);
    }

    @Override
    public void deleteAllUsers() {
        localUserDataSource.deleteAllUsers();
        localUserDataSource.refreshUsers();
    }

    @Override
    public void refreshUsers() {
        dataIsDirty = true;
    }
}
