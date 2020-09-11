package io.vasilenko.myrecipes.presentation.catalog.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogCategoryBinding
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogCategoryModel
import io.vasilenko.myrecipes.core.presentation.adapter.BaseDiffUtilItemCallback
import io.vasilenko.myrecipes.core.presentation.adapter.ListItem

class CategoriesAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(categoryItemsDelegate())
    }

    private fun categoryItemsDelegate() =
        adapterDelegateViewBinding<CatalogCategoryModel, ListItem, ItemCatalogCategoryBinding>(
            { inflater, container ->
                ItemCatalogCategoryBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                binding.title = item.title
                binding.executePendingBindings()
            }
        }
}