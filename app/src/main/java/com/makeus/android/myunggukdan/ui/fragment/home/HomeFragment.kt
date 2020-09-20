package com.makeus.android.myunggukdan.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.adapter.FragHomeViewPagerAdapter
import com.makeus.android.myunggukdan.databinding.FragHomeBinding
import com.makeus.android.myunggukdan.extension.loge
import com.makeus.android.myunggukdan.ui.listener.AddWasteItemListener
import com.makeus.android.myunggukdan.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.frag_home.*

class HomeFragment(
    val historyViewModel: HistoryViewModel
) : Fragment() {
    private lateinit var binding: FragHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_home_viewpager.adapter = FragHomeViewPagerAdapter(
            this, historyViewModel
        )
        TabLayoutMediator(frag_home_tab, frag_home_viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.frag_home_history_title)
                1 -> getString(R.string.frag_home_calendar_title)
                2 -> getString(R.string.frag_home_statistics_title)
                else -> ""
            }
        }.attach()
    }

    companion object {
        private var instance: HomeFragment? = null

        fun getInstance(
            historyViewModel: HistoryViewModel
        ) = instance
            ?: synchronized(this) {
                instance
                    ?: HomeFragment(historyViewModel).also { instance = it }
            }

        fun newInstance(historyViewModel: HistoryViewModel) = HomeFragment(historyViewModel)
    }
}