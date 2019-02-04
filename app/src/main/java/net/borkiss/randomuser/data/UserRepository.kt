package net.borkiss.randomuser.data

import net.borkiss.randomuser.data.model.User
import net.borkiss.randomuser.data.remote.RemoteUserDataSource

class UserRepository: UserDataSource {

    companion object {
        val INSTANCE = UserRepository()
    }

    private val remoteUserDataSource: UserDataSource = RemoteUserDataSource.INSTANCE

    override fun getAllUsers(callback: UserDataSource.LoadUsersCallback) {
        getFromRemoteDataSource(callback)
    }

    private fun getFromRemoteDataSource(callback: UserDataSource.LoadUsersCallback) {
        remoteUserDataSource.getAllUsers(object : UserDataSource.LoadUsersCallback {
            override fun onUsersLoaded(users: List<User>) {
                callback.onUsersLoaded(users)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

}
