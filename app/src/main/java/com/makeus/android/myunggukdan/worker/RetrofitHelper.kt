package com.makeus.android.myunggukdan.worker

import com.google.gson.GsonBuilder
import com.makeus.android.myunggukdan.data.BASE_URL
import com.makeus.android.myunggukdan.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val gson = GsonBuilder().setLenient().create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
    }

    private val userService = retrofit.create(UserService::class.java)


    // User Service
    fun signIn(idx: String, password: String) =
        userService.userSignIn(idx, password)

    fun signUp(email: String, password: String, nickName: String, wasteAmount: Int, startDay: Int, character: Int) =
        userService.userSignUp(email, password, nickName, wasteAmount, startDay, character)
}