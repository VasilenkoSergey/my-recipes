package io.vasilenko.myrecipes.presentation.catalog.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogCategoryBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogRecipeFavoriteBinding
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogCategoryModel
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogFavoriteModel
import io.vasilenko.myrecipes.presentation.common.BaseDiffUtilItemCallback
import io.vasilenko.myrecipes.presentation.common.ListItem

class CatalogHorizontalAdapter :
    AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager
            .addDelegate(favoritesDelegate())
            .addDelegate(categoriesDelegate())
    }

    private fun favoritesDelegate() =
        adapterDelegateViewBinding<CatalogFavoriteModel, ListItem, ItemCatalogRecipeFavoriteBinding>(
            { inflater, container ->
                ItemCatalogRecipeFavoriteBinding.inflate(
                    inflater,
                    container,
                    false
                )
            }
        ) {
            bind {
                binding.title = item.title
                binding.imageView.setBackgroundColor(-10354450)
                binding.executePendingBindings()
            }
        }

    private fun categoriesDelegate() =
        adapterDelegateViewBinding<CatalogCategoryModel, ListItem, ItemCatalogCategoryBinding>(
            { inflater, container ->
                ItemCatalogCategoryBinding.inflate(
                    inflater,
                    container,
                    false
                )
            }
        ) {
            bind {
                binding.title = item.title
                binding.imageView.setBackgroundColor(-10354450)
                binding.executePendingBindings()
            }
        }
}