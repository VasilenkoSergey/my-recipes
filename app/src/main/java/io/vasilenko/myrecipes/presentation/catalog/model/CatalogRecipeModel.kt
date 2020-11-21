package io.vasilenko.myrecipes.presentation.catalog.model

import io.vasilenko.myrecipes.core.presentation.adapter.ListItem

data class CatalogRecipeModel(
    val id: Long,
    val title: String,
    val image: String = ""
) : ListItem {
    override val itemId: Long = id
}