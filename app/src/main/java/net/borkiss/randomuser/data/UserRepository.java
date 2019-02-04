package net.borkiss.randomuser.data;

import android.content.Context;
import android.support.annotation.NonNull;

import net.borkiss.randomuser.data.model.User;
import net.borkiss.randomuser.data.remote.RemoteUserDataSource;

import java.util.List;

public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;

    private final UserDataSource remoteUserDataSource;


    private UserRepository(Context context) {
        remoteUserDataSource = RemoteUserDataSource.getInstance();
    }

    public static UserRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(context.getApplicationContext());
        }
        return INSTANCE;
    }



    @Override
    public void getAllUsers(@NonNull final LoadUsersCallback callback) {
        getFromRemoteDataSource(callback);
    }

    private void getFromRemoteDataSource(final LoadUsersCallback callback) {
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
}
