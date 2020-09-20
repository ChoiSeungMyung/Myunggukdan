package com.makeus.android.myunggukdan.ui.fragment.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.databinding.FragSignUpBinding
import com.makeus.android.myunggukdan.viewmodel.SignViewModel
import kotlinx.android.synthetic.main.frag_add_wasteitem.*
import kotlinx.android.synthetic.main.frag_sign_up.*
import kotlinx.android.synthetic.main.layout_controller_bar.view.*

class SignUpFragment(signViewModel: SignViewModel) : Fragment() {
    private lateinit var binding: FragSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_sign_up_controller_bar.inflate().apply {
            btn_back.setOnClickListener{
                activity?.onBackPressed()
            }
        }
    }

    companion object {
        private var instance: SignUpFragment? = null
        fun getInstance(signViewModel: SignViewModel) = instance
            ?: synchronized(this) {
                instance
                    ?: SignUpFragment(signViewModel).also { instance = it }
            }

        fun newInstance(signViewModel: SignViewModel) = SignUpFragment(signViewModel)
    }
}