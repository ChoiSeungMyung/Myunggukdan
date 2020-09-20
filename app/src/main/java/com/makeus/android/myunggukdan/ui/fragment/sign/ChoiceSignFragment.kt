package com.makeus.android.myunggukdan.ui.fragment.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.databinding.FragChoiceSignBinding
import com.makeus.android.myunggukdan.viewmodel.SignViewModel

class ChoiceSignFragment(private val signViewModel: SignViewModel) : Fragment() {
    private lateinit var binding: FragChoiceSignBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragChoiceSignBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signViewModelBinding = signViewModel
    }

    companion object {
        private var instance: ChoiceSignFragment? = null
        fun getInstance(signViewModel: SignViewModel) = instance
            ?: synchronized(this) {
                instance
                    ?: ChoiceSignFragment(signViewModel).also { instance = it }
            }

        fun newInstance(signViewModel: SignViewModel) = ChoiceSignFragment(signViewModel)
    }
}