package io.vasilenko.myrecipes.presentation.catalog.model

import io.vasilenko.myrecipes.core.presentation.adapter.ListItem

data class CatalogEmptyGroupModel(
    val title: String
) : ListItem {
    override val itemId: Long = title.hashCode().toLong()
}