package net.borkiss.randomuser.data

import io.reactivex.Single
import net.borkiss.randomuser.data.model.User

interface UserRepository {
    fun getAllUsers(): Single<List<User>>
}
