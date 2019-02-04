package net.borkiss.randomuser.data.remote


import android.util.Log

import net.borkiss.randomuser.data.UserDataSource
import net.borkiss.randomuser.data.model.ListWrapper
import net.borkiss.randomuser.data.model.User
import net.borkiss.randomuser.service.ServiceGenerator
import net.borkiss.randomuser.service.UsersService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteUserDataSource private constructor() : UserDataSource {

    companion object {
        private val TAG = RemoteUserDataSource::class.java.simpleName
        val INSTANCE = RemoteUserDataSource()
    }

    private val service: UsersService = ServiceGenerator.createService(UsersService::class.java)

    override fun getAllUsers(callback: UserDataSource.LoadUsersCallback) {
        val call = service.users
        call.enqueue(object : Callback<ListWrapper<User>> {
            override fun onResponse(call: Call<ListWrapper<User>>, response: Response<ListWrapper<User>>) {
                if (response.isSuccessful) {
                    callback.onUsersLoaded(response.body()!!.results)
                } else {
                    Log.e(TAG, "Can't load from net. Error code: " + response.code())
                    callback.onDataNotAvailable()
                }
            }

            override fun onFailure(call: Call<ListWrapper<User>>, t: Throwable) {
                Log.e(TAG, "Failure on load from net " + t.localizedMessage)
                callback.onDataNotAvailable()
            }
        })
    }
}
