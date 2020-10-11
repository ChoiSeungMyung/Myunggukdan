package com.makeus.android.myunggukdan.ui.fragment.sign.up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.databinding.FragSignUp1Binding
import com.makeus.android.myunggukdan.viewmodel.SignViewModel
import kotlinx.android.synthetic.main.frag_sign_up_1.*

class SignUpFirstFragment(
    private val signViewModel: SignViewModel
) : Fragment() {
    private lateinit var binding: FragSignUp1Binding

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
        binding = FragSignUp1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=\\S+\$).{8,}")

        binding.apply {
            signViewModelBinding = signViewModel
            fragSignUpEditNick.onFocusChangeListener = editTextFocusListener
            fragSignUpEditEmail.onFocusChangeListener = editTextFocusListener
            fragSignUpEditPassword.onFocusChangeListener = editTextFocusListener
        }

        signViewModel.run {
            nickname.observe(viewLifecycleOwner, {
                frag_sign_up_ic_nick_check.apply {
                    when(it.isNullOrBlank()) {
                        true -> {
                            postValueEnableNickName(false)
                            setImageResource(R.drawable.ic_check_off)
                        }

                        false -> {
                            postValueEnableNickName(true)
                            setImageResource(R.drawable.ic_check_on)
                        }
                    }
                }
                getValueEnableSignUp()
            })

            email.observe(viewLifecycleOwner, {
                frag_sign_up_ic_email_check.apply {
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
                getValueEnableSignUp()
            })

            password.observe(viewLifecycleOwner, {
                frag_sign_up_ic_password_check.apply {
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
                getValueEnableSignUp()
            })
        }
    }

    companion object {
        private var instance: SignUpFirstFragment? = null

        fun getInstance(
            signViewModel: SignViewModel
        ) = instance
            ?: synchronized(this) {
                instance
                    ?: SignUpFirstFragment(signViewModel).also { instance = it }
            }

        fun newInstance(signViewModel: SignViewModel) = SignUpFirstFragment(signViewModel)
    }
}