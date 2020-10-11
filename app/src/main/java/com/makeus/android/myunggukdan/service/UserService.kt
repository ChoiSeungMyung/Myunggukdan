package com.makeus.android.myunggukdan.service

import com.makeus.android.myunggukdan.data.SignInResult
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {
    @FormUrlEncoded
    @POST("/token")
    fun userSignIn(
        @Field("id") id: String,
        @Field("pw") password: String
    ): Call<SignInResult>
}