package net.borkiss.randomuser.service

import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private val BASE_URL = "https://randomuser.me/api/"
    private val TAG = RetrofitFactory::class.java.simpleName

    private val gson = GsonBuilder()
            .setDateFormat("yyyy-mm-dd HH:mm:ss")
            .setLenient()
            .create()


    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}
