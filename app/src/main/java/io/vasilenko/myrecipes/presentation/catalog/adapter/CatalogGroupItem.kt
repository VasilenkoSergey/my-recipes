package io.vasilenko.myrecipes.presentation.catalog.adapter

import io.vasilenko.myrecipes.presentation.common.ListItem

data class CatalogGroupItem(
    val title: String,
    val recipes: List<ListItem>
) : ListItem {
    override val itemId: Long = title.hashCode().toLong()
}