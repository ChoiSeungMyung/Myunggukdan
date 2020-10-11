package com.makeus.android.myunggukdan.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.data.WasteItemList
import com.makeus.android.myunggukdan.data.model.WasteItem
import com.makeus.android.myunggukdan.databinding.FragAddWasteitemBinding
import com.makeus.android.myunggukdan.extension.loge
import com.makeus.android.myunggukdan.ui.view.WasteItemView
import com.makeus.android.myunggukdan.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.frag_add_wasteitem.*
import kotlinx.android.synthetic.main.layout_controller_bar.view.*
import kotlinx.android.synthetic.main.layout_waste_item.view.*


class AddWasteItemFragment(private val historyViewModel: HistoryViewModel) : Fragment(), View.OnClickListener {
    private lateinit var binding: FragAddWasteitemBinding
    private val itemViewList: ArrayList<WasteItemView> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragAddWasteitemBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var showInputView = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_waste_item_controller_bar.inflate().apply {
            controller_bar_title.text = getString(R.string.add_waste)
            btn_back.setOnClickListener {
                activity?.onBackPressed()
            }
        }

        binding.run {
            addWasteItemList.children.forEach {
                if (it is WasteItemView) {
                    itemViewList.add(it)
                }
                it.setOnClickListener(this@AddWasteItemFragment)
            }
//            add_waste_item_waste_amount_input.setOnClickListener {
//                showInputView = !showInputView
//                when (showInputView) {
//                    true -> {
//                        dialogWrapper.visibility = View.VISIBLE
//                        frag_add_wasteitem_root.isClickable = false
//                    }
//                    false -> {
//                        dialogWrapper.visibility = View.GONE
//                        frag_add_wasteitem_root.isClickable = true
//                    }
//                }
//            }
        }
    }

    private fun initItemView() {
        WasteItemList.wasteItemList.filter { it.isSelected.get() }.forEach { it.isSelected.set(false) }
        itemViewList.forEach {
            it.waste_item_root.setBackgroundResource(R.drawable.border_waste_item_off)
        }
    }

    override fun onDestroyView() {
        WasteItemList.wasteItemList.forEach {
            it.isSelected.set(false)
        }
        historyViewModel.addWasteItem()
        super.onDestroyView()
    }

    override fun onClick(view: View?) {
        when(view) {
            waste_item_alcohol -> {
                val selected = WasteItemList.wasteItemList[0].isSelected
                initItemView()
                selected.set(!selected.get())
                when(selected.get()) {
                    true -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_on)
                    false -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_off)
                }
            }

            waste_item_cigarette -> {
                initItemView()
                val selected = WasteItemList.wasteItemList[1].isSelected
                selected.set(!selected.get())
                when(selected.get()) {
                    true -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_on)
                    false -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_off)
                }
            }

            waste_item_coffee -> {
                initItemView()
                val selected = WasteItemList.wasteItemList[2].isSelected
                selected.set(!selected.get())

                when(selected.get()) {
                    true -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_on)
                    false -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_off)
                }
            }

            waste_item_shopping -> {
                initItemView()
                val selected = WasteItemList.wasteItemList[3].isSelected
                selected.set(!selected.get())

                when(selected.get()) {
                    true -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_on)
                    false -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_off)
                }
            }

            waste_item_snack -> {
                initItemView()
                val selected = WasteItemList.wasteItemList[4].isSelected
                selected.set(!selected.get())

                when(selected.get()) {
                    true -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_on)
                    false -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_off)
                }
            }

            waste_item_taxi -> {
                initItemView()
                val selected = WasteItemList.wasteItemList[5].isSelected
                selected.set(!selected.get())

                when(selected.get()) {
                    true -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_on)
                    false -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_off)
                }
            }

            waste_item_latefood -> {
                initItemView()
                val selected = WasteItemList.wasteItemList[6].isSelected
                selected.set(!selected.get())

                when(selected.get()) {
                    true -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_on)
                    false -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_off)
                }
            }

            waste_item_game -> {
                initItemView()
                val selected = WasteItemList.wasteItemList[7].isSelected
                selected.set(!selected.get())

                when(selected.get()) {
                    true -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_on)
                    false -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_off)
                }
            }

            waste_item_etc -> {
                initItemView()
                val selected = WasteItemList.wasteItemList[8].isSelected
                selected.set(!selected.get())

                when(selected.get()) {
                    true -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_on)
                    false -> (view as WasteItemView).waste_item_root.setBackgroundResource(R.drawable.border_waste_item_off)
                }
            }
        }
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