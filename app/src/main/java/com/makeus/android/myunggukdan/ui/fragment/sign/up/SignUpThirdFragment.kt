package com.makeus.android.myunggukdan.ui.fragment.sign.up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.adapter.FragChoiceCatViewPagerAdapter
import com.makeus.android.myunggukdan.databinding.FragSignUp3Binding
import com.makeus.android.myunggukdan.viewmodel.SignViewModel

class SignUpThirdFragment(
    private val signViewModel: SignViewModel
) : Fragment() {
    private lateinit var binding: FragSignUp3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragSignUp3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            signViewModelBinding = signViewModel
            fragSignUp3ViewPager.adapter = FragChoiceCatViewPagerAdapter(this@SignUpThirdFragment)
            fragSignUp3ViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
//                    TODO: (0번 - 치즈냥, 1번 - 그레냥)으로 서버랑 맞추기 reason: 페이저 어댑터 포지션으로 맞추는게 편함
                    when (position) {
                        0 -> {
                            fragSignUpIndicator1.setImageResource(R.drawable.indicator_on)
                            fragSignUpIndicator2.setImageResource(R.drawable.indicator_off)
                            signViewModel.postValueCharacter(position + 1)
                        }
                        1 -> {
                            fragSignUpIndicator1.setImageResource(R.drawable.indicator_off)
                            fragSignUpIndicator2.setImageResource(R.drawable.indicator_on)
                            signViewModel.postValueCharacter(position + 1)
                        }
                    }
                }
            })
        }
    }

    companion object {
        private var instance: SignUpThirdFragment? = null

        fun getInstance(
            signViewModel: SignViewModel
        ) = instance
            ?: synchronized(this) {
                instance
                    ?: SignUpThirdFragment(signViewModel).also { instance = it }
            }

        fun newInstance(signViewModel: SignViewModel) = SignUpThirdFragment(signViewModel)
    }
}