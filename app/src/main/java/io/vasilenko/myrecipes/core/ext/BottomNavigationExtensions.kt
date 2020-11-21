package io.vasilenko.myrecipes.core.ext

import android.util.Log
import androidx.core.view.forEach
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.disableTooltipText() {
    try {
        val menuViewField = this.javaClass.getDeclaredField("menuView")
        menuViewField.isAccessible = true
        val menuView = menuViewField.get(this) as BottomNavigationMenuView
        menuView.forEach {
            it.setOnLongClickListener { true }
        }
    } catch (e: Exception) {
        Log.w("BottomNavigationView", e)
    }
}