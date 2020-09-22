package com.makeus.android.myunggukdan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SignViewModel(application: Application) : AndroidViewModel(application) {
    enum class SignState {
        SignUp,
        SignIn,
        SignFail,
        SignSuccess
    }

    private val _signState: MutableLiveData<SignState> = MutableLiveData(SignState.SignFail)
    val signState: LiveData<SignState> = _signState

    private val _loginCheck: MutableLiveData<Boolean> = MutableLiveData(false)
    val loginCheck: LiveData<Boolean> = _loginCheck

    private fun postValueIsLogin(isLogin: Boolean) {
        _loginCheck.postValue(isLogin)
    }

    fun postValueSignState(state: SignState) {
        when (state) {
            SignState.SignFail, SignState.SignIn, SignState.SignUp -> postValueIsLogin(false)
            SignState.SignSuccess -> postValueIsLogin(true)
        }
        _signState.postValue(state)
    }

    fun trySignUp() {
        // 로그인 시도
//        when(response) {
//            true -> {
//
//            }
//
//            false -> {
//
//            }
//        }
    }
}