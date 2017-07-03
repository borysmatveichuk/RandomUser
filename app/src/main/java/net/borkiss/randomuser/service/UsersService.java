package net.borkiss.randomuser.service;

import net.borkiss.randomuser.data.model.ListWrapper;
import net.borkiss.randomuser.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersService {

    //get array with 100 user without info about login and id
    @GET("?results=100&exc=login,id")
    Call<ListWrapper<User>> getUsers();
}

