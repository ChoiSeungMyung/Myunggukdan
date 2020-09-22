package com.makeus.android.myunggukdan.ui.fragment.sign

import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.databinding.FragSignInBinding
import com.makeus.android.myunggukdan.extension.loge
import com.makeus.android.myunggukdan.viewmodel.SignViewModel
import kotlinx.android.synthetic.main.frag_sign_in.*
import kotlinx.android.synthetic.main.layout_controller_bar.view.*

class SignInFragment(private val signViewModel: SignViewModel) : Fragment() {
    private lateinit var binding: FragSignInBinding

    private val editTextFocusListener = View.OnFocusChangeListener { view, hasFocus ->
        when(hasFocus) {
            true -> view?.setBackgroundResource(R.drawable.bg_focus_edit_text)
            false -> view?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorGreyVeryLight))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_sign_in_controller_bar.inflate().apply {
            btn_back.setOnClickListener{
                activity?.onBackPressed()
            }
        }
        binding.signViewModelBinding = signViewModel

        frag_sign_in_edit_email.onFocusChangeListener = editTextFocusListener
        frag_sign_in_edit_password.onFocusChangeListener = editTextFocusListener
        frag_sign_in_checkbox_view_password.setOnCheckedChangeListener { _, isChecked ->
            when(isChecked) {
                true -> frag_sign_in_edit_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                false -> frag_sign_in_edit_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    companion object {
        private var instance: SignInFragment? = null
        fun getInstance(signViewModel: SignViewModel) = instance
            ?: synchronized(this) {
                instance
                    ?: SignInFragment(signViewModel).also { instance = it }
            }

        fun newInstance(signViewModel: SignViewModel) = SignInFragment(signViewModel)
    }
}