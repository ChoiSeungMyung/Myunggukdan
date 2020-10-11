package com.makeus.android.myunggukdan.data

data class SignInResult(
    val jwt: String,
    val isSuccess: String,
    val code: Int,
    val message: String
) {
    override fun toString(): String {
        return "jwt: $jwt\nisSuccess: $isSuccess\ncode: $code\nmessage: $message"
    }
}