package io.vasilenko.myrecipes.presentation.catalog.model

import io.vasilenko.myrecipes.presentation.common.ListItem

data class CatalogGroupModel(
    val title: String,
    val recipes: List<ListItem>
) : ListItem {
    override val itemId: Long = title.hashCode().toLong()
}