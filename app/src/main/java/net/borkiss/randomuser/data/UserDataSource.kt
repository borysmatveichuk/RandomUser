package net.borkiss.randomuser.data

import net.borkiss.randomuser.data.model.User

interface UserDataSource {

    interface LoadUsersCallback {
        fun onUsersLoaded(users: List<User>)
        fun onDataNotAvailable()
    }

    fun getAllUsers(callback: LoadUsersCallback)
}
