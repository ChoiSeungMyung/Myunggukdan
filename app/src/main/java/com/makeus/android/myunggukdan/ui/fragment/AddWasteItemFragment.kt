package com.makeus.android.myunggukdan.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.databinding.FragAddWasteitemBinding
import com.makeus.android.myunggukdan.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.frag_add_wasteitem.*
import kotlinx.android.synthetic.main.layout_controller_bar.view.*

class AddWasteItemFragment(private val historyViewModel: HistoryViewModel) : Fragment() {
    private lateinit var binding: FragAddWasteitemBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragAddWasteitemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_waste_item_controller_bar.inflate().apply {
            controller_bar_title.text = getString(R.string.add_waste)
            btn_back.setOnClickListener{
                activity?.onBackPressed()
            }
        }
        binding.run {

        }
    }

    override fun onDestroyView() {
        historyViewModel.addWasteItem()
        super.onDestroyView()
    }

    companion object {
        private var instance: AddWasteItemFragment? = null

        fun getInstance(historyViewModel: HistoryViewModel) = instance
            ?: synchronized(this) {
                instance
                    ?: AddWasteItemFragment(historyViewModel).also { instance = it }
            }

        fun newInstance(historyViewModel: HistoryViewModel) = AddWasteItemFragment(historyViewModel)
    }
}