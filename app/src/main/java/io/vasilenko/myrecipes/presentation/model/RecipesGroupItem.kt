package io.vasilenko.myrecipes.presentation.model

import io.vasilenko.myrecipes.presentation.common.ListItem

data class RecipesGroupItem(
    val title: String,
    val recipes: List<ListItem>
) : ListItem {
    override val itemId: Long = title.hashCode().toLong()
}