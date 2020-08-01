package com.makeus.android.myunggukdan.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.databinding.FragHomeHistoryBinding

class HistoryFragment : Fragment() {
    private lateinit var binding: FragHomeHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragHomeHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }
}