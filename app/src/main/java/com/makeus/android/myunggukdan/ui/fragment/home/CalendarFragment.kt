package com.makeus.android.myunggukdan.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.databinding.FragHomeCalendarBinding
import com.makeus.android.myunggukdan.databinding.FragHomeHistoryBinding

class CalendarFragment : Fragment() {
    private lateinit var binding: FragHomeCalendarBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragHomeCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }
}