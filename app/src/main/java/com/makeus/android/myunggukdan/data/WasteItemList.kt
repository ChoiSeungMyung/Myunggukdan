package com.makeus.android.myunggukdan.data

import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.data.model.WasteItem

object WasteItemList {
    private val alcohol = WasteItem(
        "술",
        R.drawable.ic_beer
    )
    private val cigarette = WasteItem(
        "담배",
        R.drawable.ic_cigarette
    )
    private val coffee = WasteItem(
        "커피",
        R.drawable.ic_coffee
    )
    private val shopping = WasteItem(
        "충동 쇼핑",
        R.drawable.ic_shopping
    )
    private val snack = WasteItem(
        "군것질",
        R.drawable.ic_snack
    )
    private val taxi = WasteItem(
        "택시",
        R.drawable.ic_taxi
    )
    private val lateFood = WasteItem(
        "야식",
        R.drawable.ic_latefood
    )
    private val game = WasteItem(
        "게임",
        R.drawable.ic_game
    )
    private val etc = WasteItem(
        "직접입력",
        R.drawable.ic_money
    )

    val wasteItemList = listOf(
        alcohol,
        cigarette,
        coffee,
        shopping,
        snack,
        taxi,
        lateFood,
        game,
        etc
    )
}