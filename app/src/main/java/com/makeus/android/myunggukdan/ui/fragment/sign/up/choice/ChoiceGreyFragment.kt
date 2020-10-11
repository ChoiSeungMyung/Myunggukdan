package com.makeus.android.myunggukdan.ui.fragment.sign.up.choice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.databinding.FragChoiceGreyBinding

class ChoiceGreyFragment : Fragment() {
    private lateinit var binding: FragChoiceGreyBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragChoiceGreyBinding.inflate(inflater, container, false)
        return binding.root
    }
}