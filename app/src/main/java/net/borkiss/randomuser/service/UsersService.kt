package net.borkiss.randomuser.service

import io.reactivex.Single
import net.borkiss.randomuser.data.model.ListWrapper
import net.borkiss.randomuser.data.model.User

import retrofit2.http.GET

interface UsersService {

    //get array with 100 user without info about login and id
    @GET("?results=100&exc=login,id")
    fun getUsers(): Single<ListWrapper<User>>
}

