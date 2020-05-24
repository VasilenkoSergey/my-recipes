package io.vasilenko.myrecipes.presentation.model

import io.vasilenko.myrecipes.presentation.common.ListItem

data class BigRecipeItem(
    val id: Long,
    val title: String,
    val image: String
) : ListItem {
    override val itemId: Long = id
}