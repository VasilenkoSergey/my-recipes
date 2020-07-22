package io.vasilenko.myrecipes.presentation.model

import io.vasilenko.myrecipes.presentation.common.ListItem

data class CatalogEmptyItem(
    val title: String
) : ListItem {
    override val itemId: Long = title.hashCode().toLong()
}