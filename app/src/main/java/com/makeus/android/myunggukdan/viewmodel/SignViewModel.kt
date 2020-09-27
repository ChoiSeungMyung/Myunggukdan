package com.makeus.android.myunggukdan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.makeus.android.myunggukdan.extension.loge

class SignViewModel(application: Application) : AndroidViewModel(application) {
    enum class SignState {
        SignUp,
        SignIn,
        SignFail,
        SignSuccess,
        SignFindPassword
    }
    val testToast: MutableLiveData<String> = MutableLiveData()
    private val _signState: MutableLiveData<SignState> = MutableLiveData(SignState.SignFail)
    val signState: LiveData<SignState> = _signState

    private var enableEmail: Boolean = false
    private var enablePassword: Boolean = false

    private val _enableSignIn: MutableLiveData<Boolean> = MutableLiveData(false)
    val enableSignIn: LiveData<Boolean> = _enableSignIn

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    private val _loginCheck: MutableLiveData<Boolean> = MutableLiveData(false)
    val loginCheck: LiveData<Boolean> = _loginCheck

    fun postValueEnableEmail(isEnable: Boolean) {
        enableEmail = isEnable
    }
    fun postValueEnablePassword(isEnable: Boolean) {
        enablePassword = isEnable
    }

    fun getValueEnableSignIn() {
        _enableSignIn.postValue(enableEmail && enablePassword)
    }

    fun postValueSignState(state: SignState) {
        when (state) {
            SignState.SignFail, SignState.SignIn, SignState.SignUp -> _loginCheck.postValue(false)
            SignState.SignSuccess -> _loginCheck.postValue(true)
            else -> _loginCheck.postValue(false)
        }
        _signState.postValue(state)
    }

    fun trySignIn() {
        // 로그인 시도
        loge("로그인 시도 :::: id = ${email.value} , password = ${password.value}")
        testToast.postValue("id = ${email.value}\npassword = ${password.value}")
        postValueSignState(SignState.SignSuccess)
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