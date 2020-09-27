package com.makeus.android.myunggukdan.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.databinding.FragHomeStatisticsBinding

class StatisticsFragment : Fragment() {
    private lateinit var binding: FragHomeStatisticsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragHomeStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private var instance: StatisticsFragment? = null

        fun getInstance() = instance
            ?: synchronized(this) {
                instance
                    ?: StatisticsFragment().also { instance = it }
            }

        fun newInstance() = StatisticsFragment()
    }
}