package io.vasilenko.myrecipes.presentation.catalog.model

import io.vasilenko.myrecipes.presentation.common.ListItem

data class CatalogCategoryModel(
    val id: Long,
    val title: String,
    val image: String
) : ListItem {
    override val itemId: Long = id
}