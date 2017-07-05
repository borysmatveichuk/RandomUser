package net.borkiss.randomuser.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import net.borkiss.randomuser.data.UserDataSource;
import net.borkiss.randomuser.data.model.DaoMaster;
import net.borkiss.randomuser.data.model.DaoSession;
import net.borkiss.randomuser.data.model.User;
import net.borkiss.randomuser.data.model.UserDao;
import net.borkiss.randomuser.data.remote.RemoteUserDataSource;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class LocalUserDataSource implements UserDataSource {

    private static final String TAG = RemoteUserDataSource.class.getSimpleName();

    private static LocalUserDataSource INSTANCE;
    private static final String DB_NAME = "users-db";
    private UserDao userDao;

    private LocalUserDataSource(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();
        userDao = daoSession.getUserDao();
    }

    public static LocalUserDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalUserDataSource(context);
        }

        return INSTANCE;
    }

    @Override
    public void getAllUsers(@NonNull LoadUsersCallback callback) {
        List<User> users = userDao.loadAll();
        if (users != null && !users.isEmpty()) {
            callback.onUsersLoaded(users);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void getUser(long userId, GetUserCallback callback) {
        User user = userDao.load(userId);
        if (user != null) {
            callback.onUserLoaded(user);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public long saveUser(User user) {
        return userDao.insertOrReplace(user);
    }

    @Override
    public void deleteAllUsers() {
        userDao.deleteAll();
    }

    /**
     * Not required in local data source. see {@link net.borkiss.randomuser.data.UserRepository}
     */
    @Override
    public void refreshUsers() {
    }
}
