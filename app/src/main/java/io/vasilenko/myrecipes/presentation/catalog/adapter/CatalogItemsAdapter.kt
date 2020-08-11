package io.vasilenko.myrecipes.presentation.catalog.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogCategoryBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogEmptyBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogRecipeBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogRecipeFavoriteBinding
import io.vasilenko.myrecipes.presentation.common.BaseDiffUtilItemCallback
import io.vasilenko.myrecipes.presentation.common.ListItem

class CatalogItemsAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager
            .addDelegate(favoriteRecipesDelegate())
            .addDelegate(categoriesDelegate())
            .addDelegate(recipesDelegate())
            .addDelegate(emptyDelegate())
    }

    private fun favoriteRecipesDelegate() =
        adapterDelegateViewBinding<CatalogFavoriteRecipeItem, ListItem, ItemCatalogRecipeFavoriteBinding>(
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

    private fun recipesDelegate() =
        adapterDelegateViewBinding<CatalogRecipeItem, ListItem, ItemCatalogRecipeBinding>(
            { inflater, container -> ItemCatalogRecipeBinding.inflate(inflater, container, false) }
        ) {
            bind {
                binding.title = item.title
                binding.imageView.setBackgroundColor(-10354450)
                binding.executePendingBindings()
            }
        }

    private fun emptyDelegate() =
        adapterDelegateViewBinding<CatalogEmptyItem, ListItem, ItemCatalogEmptyBinding>(
            { inflater, container -> ItemCatalogEmptyBinding.inflate(inflater, container, false) }
        ) {
            bind {
                binding.titleTextView.text = item.title
            }
        }

    private fun categoriesDelegate() =
        adapterDelegateViewBinding<CatalogCategoryItem, ListItem, ItemCatalogCategoryBinding>(
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