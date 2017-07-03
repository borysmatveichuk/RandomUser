package net.borkiss.randomuser.data.local;

import android.support.annotation.NonNull;

import net.borkiss.randomuser.data.UserDataSource;
import net.borkiss.randomuser.data.model.User;
import net.borkiss.randomuser.data.remote.RemoteUserDataSource;

public class LocalUserDataSource implements UserDataSource {

    private static final String TAG = RemoteUserDataSource.class.getSimpleName();

    private static LocalUserDataSource INSTANCE;

    private LocalUserDataSource() {

    }

    public static LocalUserDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalUserDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void getAllUsers(@NonNull LoadUsersCallback callback) {

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
