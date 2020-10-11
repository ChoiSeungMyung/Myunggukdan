package com.makeus.android.myunggukdan.adapter.binding

import androidx.databinding.BindingAdapter
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.ui.view.WasteItemView
import kotlinx.android.synthetic.main.layout_waste_item.view.*


@BindingAdapter("waste_item_background")
fun setBackground(view: WasteItemView, background: Boolean) {
    when(background) {
        true -> view.waste_item_root.setBackgroundResource(R.drawable.border_waste_item_on)
        false -> view.waste_item_root.setBackgroundResource(R.drawable.border_waste_item_off)
    }
}