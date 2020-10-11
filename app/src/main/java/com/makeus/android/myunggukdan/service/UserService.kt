package com.makeus.android.myunggukdan.service

import com.makeus.android.myunggukdan.data.retrofit.SignInResult
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {
    @FormUrlEncoded
    @POST("/user")
    fun userSignUp(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("nickName") nickName: String,
        @Field("wasteAmount") wasteAmount: Int,
        @Field("startDay") startDay: Int,
        @Field("character") character: Int
    ): Call<SignInResult>

    @FormUrlEncoded
    @POST("/token")
    fun userSignIn(
        @Field("id") id: String,
        @Field("pw") password: String
    ): Call<SignInResult>
}