package com.makeus.android.myunggukdan.extension

import androidx.fragment.app.FragmentManager

fun FragmentManager.clearBackStack() {
    repeat(backStackEntryCount) {
        popBackStackImmediate()
    }
}