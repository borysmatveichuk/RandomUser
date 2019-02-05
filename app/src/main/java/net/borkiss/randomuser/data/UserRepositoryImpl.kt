package net.borkiss.randomuser.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import net.borkiss.randomuser.data.model.User
import net.borkiss.randomuser.service.UsersService

class UserRepositoryImpl(
        private val usersService: UsersService
): UserRepository {

    override fun getAllUsers(): Single<List<User>> {
        return usersService.getUsers()
                .map { wrapper ->
                    wrapper.results
                }.observeOn(AndroidSchedulers.mainThread())
    }

}
