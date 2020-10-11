package com.makeus.android.myunggukdan.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.makeus.android.myunggukdan.ui.fragment.sign.up.SignUpFirstFragment
import com.makeus.android.myunggukdan.ui.fragment.sign.up.SignUpSecondFragment
import com.makeus.android.myunggukdan.ui.fragment.sign.up.SignUpThirdFragment
import com.makeus.android.myunggukdan.viewmodel.SignViewModel

class FragSignUpViewPagerAdapter(
    fragment: Fragment,
    signViewModel: SignViewModel
) : FragmentStateAdapter(fragment) {
    private val signUpFirstFragment by lazy {
        SignUpFirstFragment.newInstance(signViewModel)
    }
    private val signUpSecondFragment by lazy {
        SignUpSecondFragment.newInstance(signViewModel)
    }
    private val signUpThirdFragment by lazy {
        SignUpThirdFragment.newInstance(signViewModel)
    }

    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> signUpFirstFragment
            1 -> signUpSecondFragment
            2 -> signUpThirdFragment
            else -> throw IllegalStateException("fail to create sign up fragment $position")
        }
}