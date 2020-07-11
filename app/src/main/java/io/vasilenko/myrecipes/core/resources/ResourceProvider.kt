package io.vasilenko.myrecipes.core.resources

import androidx.annotation.StringRes

interface ResourceProvider {

    fun string(@StringRes id: Int): String
}