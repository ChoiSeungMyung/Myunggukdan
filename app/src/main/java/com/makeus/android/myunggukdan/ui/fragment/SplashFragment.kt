package com.makeus.android.myunggukdan.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.databinding.FragSplashBinding
import com.makeus.android.myunggukdan.viewmodel.SignViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment(private val signViewModel: SignViewModel) : Fragment() {
    private lateinit var binding: FragSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            delay(300)
//            signViewModel.postValueSignState(SignViewModel.SignState.SignSuccess)
            signViewModel.postValueSignState(SignViewModel.SignState.SignFail)
        }

        /**
         * 로그인 여부 판단
         * datastore에 저장되어 있는 아이디, 비밀번호가 있다면
         *      ㄴ 로그인 시도
         *          ㄴ 1. 로그인 성공
         *          ㄴ 2. 로그인 실패(ex: 인터넷 연결, 없는 아이디)
         * datastore에 저장되어 있는 아이디, 비밀번호가 없다면
         *      ㄴ 로그인, 회원가입 선택화면 이동
         *          ㄴ 1. 로그인 화면 -> 아이디, 비밀번호 입력받고 로그인 시도
         *              ㄴ 1-1. 로그인 성공 : 아이디, 비밀번호 datastore에 저장 후 진행
         *              ㄴ 1-2. 로그인 실패 : 실패 이유 전달 후 재진행
         *          ㄴ 2. 회원가입 선택화면 이동
         *              1. 메일, 닉네임, 비밀번호 등등 설정 후 회원가입 진행
         *              2. 회원가입
         *                  2-1. 회원가입 성공 : 아이디, 비밀번호 datastore에 저장 후 진행
         *                  2-2. 회원가입 실패 : 회원가입 실패 이유 전달 후 재진행
         */
    }

    companion object {
        private var instance: SplashFragment? = null
        fun getInstance(signViewModel: SignViewModel) = instance
            ?: synchronized(this) {
                instance
                    ?: SplashFragment(signViewModel).also { instance = it }
            }

        fun newInstance(signViewModel: SignViewModel) = SplashFragment(signViewModel)
    }
}