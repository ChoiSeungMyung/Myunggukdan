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
        binding = FragSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=\\S+\$).{8,}")

        frag_sign_in_controller_bar.inflate().apply {
            btn_back.setOnClickListener {
                activity?.onBackPressed()
            }
        }
        binding.apply {
            signViewModelBinding = signViewModel
            fragSignInEditEmail.onFocusChangeListener = editTextFocusListener
            fragSignInEditPassword.onFocusChangeListener = editTextFocusListener

            fragSignInCheckboxViewPassword.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    true -> fragSignInEditPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    false -> fragSignInEditPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
                fragSignInEditPassword.setSelection(fragSignInEditPassword.text.length)
            }
        }

        signViewModel.run {
            email.observe(viewLifecycleOwner, {
                frag_sign_in_ic_email_check.apply {
                    when (android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
                        true -> {
                            postValueEnableEmail(true)
                            setImageResource(R.drawable.ic_check_on)
                        }
                        false -> {
                            postValueEnableEmail(false)
                            setImageResource(R.drawable.ic_check_off)
                        }
                    }
                }
                getValueEnableSignIn()
            })

            password.observe(viewLifecycleOwner, {
                frag_sign_in_ic_password_check.apply {
                    when (passwordRegex.matches(it)) {
                        true -> {
                            postValueEnablePassword(true)
                            setImageResource(R.drawable.ic_check_on)
                        }
                        false -> {
                            postValueEnablePassword(false)
                            setImageResource(R.drawable.ic_check_off)
                        }
                    }
                }
                getValueEnableSignIn()
            })

            enableSignIn.observe(viewLifecycleOwner, {
                when (it) {
                    true -> {
                        frag_btn_sign_in.apply {
                            setBackgroundResource(R.drawable.bg_btn)
                            isClickable = true
                        }
                    }
                    false -> {
                        frag_btn_sign_in.apply {
                            setBackgroundResource(R.drawable.bg_btn_off)
                            isClickable = false
                        }
                    }
                }
            })
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