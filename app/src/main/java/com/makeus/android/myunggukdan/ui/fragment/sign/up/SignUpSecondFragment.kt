package com.makeus.android.myunggukdan.ui.fragment.sign.up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.databinding.FragSignUp2Binding
import com.makeus.android.myunggukdan.viewmodel.SignViewModel

class SignUpSecondFragment(
    private val signViewModel: SignViewModel
) : Fragment() {
    private lateinit var binding: FragSignUp2Binding

    private val editTextFocusListener = View.OnFocusChangeListener { view, hasFocus ->
        when (hasFocus) {
            true -> view?.setBackgroundResource(R.drawable.bg_focus_edit_text)
            false -> view?.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorGreyVeryLight
                )
            )
        }
    }

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
            fragSignUp2EditSetAmount.onFocusChangeListener = editTextFocusListener
            fragSignUp2EditStartDay.onFocusChangeListener = editTextFocusListener
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