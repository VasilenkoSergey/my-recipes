package io.vasilenko.myrecipes.presentation.catalog.model

import io.vasilenko.myrecipes.presentation.common.ListItem

data class CatalogEmptyGroupModel(
    val title: String
) : ListItem {
    override val itemId: Long = title.hashCode().toLong()
}