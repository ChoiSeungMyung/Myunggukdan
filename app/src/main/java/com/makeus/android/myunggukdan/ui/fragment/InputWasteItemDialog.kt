package com.makeus.android.myunggukdan.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.makeus.android.myunggukdan.databinding.DialogInputWasteItemAmoutBinding


class InputWasteItemDialog : BottomSheetDialogFragment() {
    private lateinit var binding: DialogInputWasteItemAmoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogInputWasteItemAmoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private var instance: InputWasteItemDialog? = null
        fun getInstance() = instance
            ?: synchronized(this) {
                instance
                    ?: InputWasteItemDialog().also { instance = it }
            }
    }
}