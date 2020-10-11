package com.makeus.android.myunggukdan.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.makeus.android.myunggukdan.ui.fragment.sign.up.choice.ChoiceCheezeFragment
import com.makeus.android.myunggukdan.ui.fragment.sign.up.choice.ChoiceGreyFragment

class FragChoiceCatViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment =
        when(position) {
            0 -> ChoiceCheezeFragment()
            1 -> ChoiceGreyFragment()
            else -> throw IllegalStateException("fail to create choice fragment $position")
        }
}