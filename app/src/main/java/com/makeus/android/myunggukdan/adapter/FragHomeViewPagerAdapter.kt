package com.makeus.android.myunggukdan.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.makeus.android.myunggukdan.ui.fragment.home.CalendarFragment
import com.makeus.android.myunggukdan.ui.fragment.home.HistoryFragment
import com.makeus.android.myunggukdan.ui.fragment.home.StatisticsFragment
import com.makeus.android.myunggukdan.ui.listener.AddWasteItemListener
import com.makeus.android.myunggukdan.viewmodel.HistoryViewModel

class FragHomeViewPagerAdapter(
    fragment: Fragment,
    private val historyViewModel: HistoryViewModel
) : FragmentStateAdapter(fragment) {
    private val historyFragment by lazy {
        HistoryFragment.getInstance(historyViewModel)
    }

    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> historyFragment
            1 -> CalendarFragment()
            2 -> StatisticsFragment()
            else -> throw IllegalStateException()
        }
}