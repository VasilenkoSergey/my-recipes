package io.vasilenko.myrecipes.presentation.catalog.model

import io.vasilenko.myrecipes.core.presentation.adapter.ListItem

data class CatalogCategoriesGroupModel(
    val title: String,
    val recipes: List<ListItem>
) : ListItem {
    override val itemId: Long = title.hashCode().toLong()
}