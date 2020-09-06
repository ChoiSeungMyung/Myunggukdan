package com.makeus.android.myunggukdan.ui.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.makeus.android.myunggukdan.R
import kotlinx.android.synthetic.main.layout_waste_item.view.*

class WasteItem(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.layout_waste_item, this)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.WasteItem, 0, 0)
        try {
            val drawable = typedArray.getDrawable(R.styleable.WasteItem_waste_item_icon)
            Glide.with(this)
                .load(drawable)
                .into(waste_item_icon)

            val iconSize = typedArray.getDimension(
                R.styleable.WasteItem_waste_item_icon_size,
                resources.getDimension(R.dimen.dp_60)
            )
            waste_item_icon.layoutParams.run {
                width = iconSize.toInt()
                height = iconSize.toInt()
                waste_item_icon.layoutParams = this
            }

            waste_item_text.apply {
                text = typedArray.getText(R.styleable.WasteItem_waste_item_text)
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    typedArray.getDimensionPixelSize(
                        R.styleable.WasteItem_waste_item_text_size,
                        resources.getDimension(R.dimen.dp_12).toInt()
                    ).toFloat()
                )
                setTextColor(
                    typedArray.getColor(
                        R.styleable.WasteItem_waste_item_text_color,
                        ContextCompat.getColor(context, R.color.colorBlackTwo)
                    )
                )
            }
        } finally {
            typedArray.recycle()
        }
    }

    fun setImage(resource: Int) =
        Glide.with(this)
            .load(resource)
            .into(waste_item_icon)

    fun setText(text: String) {
        waste_item_text.text = text
    }
}