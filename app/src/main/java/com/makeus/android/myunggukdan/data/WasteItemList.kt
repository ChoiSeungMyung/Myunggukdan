package com.makeus.android.myunggukdan.data

import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.data.model.WastedItem

object WasteItemList {
    private val alcohol = WastedItem(
        title = "술",
        price = 10_000,
        imageResourceId = R.drawable.ic_beer
    )
    private val cigarette = WastedItem(
        title = "담배",
        price = 4_500,
        imageResourceId = R.drawable.ic_cigarette
    )
    private val coffee = WastedItem(
        title = "커피",
        price = 4_100,
        imageResourceId = R.drawable.ic_coffee
    )
    private val shopping = WastedItem(
        title = "충동 쇼핑",
        price = 20_000,
        imageResourceId = R.drawable.ic_shopping
    )
    private val snack = WastedItem(
        title = "군것질",
        price = 5_000,
        imageResourceId = R.drawable.ic_snack
    )
    private val taxi = WastedItem(
        title = "택시",
        price = 10_000,
        imageResourceId = R.drawable.ic_taxi
    )
    private val lateFood = WastedItem(
        title = "야식",
        price = 20_000,
        imageResourceId = R.drawable.ic_latefood
    )
    private val game = WastedItem(
        title ="게임",
        price = 10_000,
        imageResourceId = R.drawable.ic_game
    )
    private val etc = WastedItem(
        title = "직접입력",
        price = 0,
        imageResourceId = R.drawable.ic_money
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