package io.vasilenko.myrecipes.presentation.catalog.adapter

import io.vasilenko.myrecipes.presentation.common.ListItem

data class CatalogGroupEmptyItem(
    val title: String
) : ListItem {
    override val itemId: Long = title.hashCode().toLong()
}