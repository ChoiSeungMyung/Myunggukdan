package com.makeus.android.myunggukdan.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.makeus.android.myunggukdan.ui.fragment.home.CalendarFragment
import com.makeus.android.myunggukdan.ui.fragment.home.HistoryFragment
import com.makeus.android.myunggukdan.ui.fragment.home.StatisticsFragment

class FragHomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> HistoryFragment()
            1 -> CalendarFragment()
            2 -> StatisticsFragment()
            else -> throw IllegalStateException()
        }
}