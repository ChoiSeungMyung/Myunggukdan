package com.makeus.android.myunggukdan.ui.fragment.sign.up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.databinding.FragSignUp2Binding
import com.makeus.android.myunggukdan.viewmodel.SignViewModel

class SignUpSecondFragment(
    private val signViewModel: SignViewModel
) : Fragment() {
    private lateinit var binding: FragSignUp2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragSignUp2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            signViewModelBinding = signViewModel
        }
    }

    companion object {
        private var instance: SignUpSecondFragment? = null

        fun getInstance(
            signViewModel: SignViewModel
        ) = instance
            ?: synchronized(this) {
                instance
                    ?: SignUpSecondFragment(signViewModel).also { instance = it }
            }

        fun newInstance(signViewModel: SignViewModel) = SignUpSecondFragment(signViewModel)
    }
}