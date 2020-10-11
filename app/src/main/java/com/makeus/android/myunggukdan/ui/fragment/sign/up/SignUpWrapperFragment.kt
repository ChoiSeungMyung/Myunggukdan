package com.makeus.android.myunggukdan.ui.fragment.sign.up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.makeus.android.myunggukdan.adapter.FragSignUpViewPagerAdapter
import com.makeus.android.myunggukdan.databinding.FragSignUpWrapperBinding
import com.makeus.android.myunggukdan.extension.loge
import com.makeus.android.myunggukdan.viewmodel.SignViewModel
import kotlinx.android.synthetic.main.frag_sign_up_wrapper.*
import kotlinx.android.synthetic.main.layout_controller_bar.view.*

class SignUpWrapperFragment(
    private val signViewModel: SignViewModel
) : Fragment() {
    private lateinit var binding: FragSignUpWrapperBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragSignUpWrapperBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        frag_sign_up_controller_bar.inflate().apply {
            btn_back.setOnClickListener {
                activity?.onBackPressed()
            }
        }

        binding.apply {
            fragSignUpViewPager.adapter = FragSignUpViewPagerAdapter(this@SignUpWrapperFragment, signViewModel)
            fragSignUpViewPager.isSaveEnabled = false

            fragSignUpViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    fragSignUpWrapperProgress.progress = position + 1
                }
            })
        }

        signViewModel.run {
            enableSignUp.observe(viewLifecycleOwner, {
                when(it) {
                    true -> {
//                        binding.fragSignUpViewPager.currentItem = 1
//                        loge("${binding.fragSignUpViewPager.currentItem}")
                    }
                }
            })
        }
    }

    companion object {
        private var instance: SignUpWrapperFragment? = null

        fun getInstance(
            signViewModel: SignViewModel
        ) = instance
            ?: synchronized(this) {
                instance
                    ?: SignUpWrapperFragment(signViewModel).also { instance = it }
            }

        fun newInstance(signViewModel: SignViewModel) = SignUpWrapperFragment(signViewModel)
    }
}