package net.borkiss.randomuser.data.remote;


import androidx.annotation.NonNull;
import android.util.Log;

import net.borkiss.randomuser.data.UserDataSource;
import net.borkiss.randomuser.data.model.ListWrapper;
import net.borkiss.randomuser.data.model.User;
import net.borkiss.randomuser.service.ServiceGenerator;
import net.borkiss.randomuser.service.UsersService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteUserDataSource implements UserDataSource {

    private static final String TAG = RemoteUserDataSource.class.getSimpleName();

    private static RemoteUserDataSource INSTANCE;
    private final UsersService service;

    private RemoteUserDataSource() {
        service = ServiceGenerator.createService(UsersService.class);
    }

    public static RemoteUserDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteUserDataSource();
        }

        return INSTANCE;
    }


    @Override
    public void getAllUsers(@NonNull final LoadUsersCallback callback) {
        Call<ListWrapper<User>> call = service.getUsers();
        call.enqueue(new Callback<ListWrapper<User>>() {
            @Override
            public void onResponse(Call<ListWrapper<User>> call, Response<ListWrapper<User>> response) {
                if (response.isSuccessful()) {
                    callback.onUsersLoaded(response.body().getResults());
                } else {
                    Log.e(TAG, "Can't load from net. Error code: " + response.code());
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<ListWrapper<User>> call, Throwable t) {
                Log.e(TAG, "Failure on load from net " + t.getLocalizedMessage());
                callback.onDataNotAvailable();
            }
        });
    }

}
