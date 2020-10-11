package com.makeus.android.myunggukdan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.makeus.android.myunggukdan.data.SignInResult
import com.makeus.android.myunggukdan.extension.loge
import com.makeus.android.myunggukdan.worker.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    private var enableNickName: Boolean = false

    private val _enableSignIn: MutableLiveData<Boolean> = MutableLiveData(false)
    val enableSignIn: LiveData<Boolean> = _enableSignIn

    private val _enableSignUp: MutableLiveData<Boolean> = MutableLiveData(false)
    val enableSignUp: LiveData<Boolean> = _enableSignUp

    val nickname: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    private val _loginCheck: MutableLiveData<Boolean> = MutableLiveData(false)
    val loginCheck: LiveData<Boolean> = _loginCheck

    fun postValueEnableNickName(isEnable: Boolean) {
        enableNickName = isEnable
    }
    fun postValueEnableEmail(isEnable: Boolean) {
        enableEmail = isEnable
    }
    fun postValueEnablePassword(isEnable: Boolean) {
        enablePassword = isEnable
    }

    fun getValueEnableSignIn() {
        _enableSignIn.postValue(enableEmail && enablePassword)
    }

    fun getValueEnableSignUp() {
        _enableSignUp.postValue(enableNickName && enableEmail && enablePassword)
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
        if (email.value != null && password.value != null) {
            RetrofitHelper.signIn(email.value!!, password.value!!).enqueue(object : Callback<SignInResult> {
                override fun onResponse(
                    call: Call<SignInResult>,
                    response: Response<SignInResult>
                ) {
                    loge(response.body().toString())
                    postValueSignState(SignState.SignSuccess)
                }

                override fun onFailure(call: Call<SignInResult>, t: Throwable) {
                    postValueSignState(SignState.SignFail)
                }
            })
        }
    }

}