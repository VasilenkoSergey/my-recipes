package io.vasilenko.myrecipes.presentation.catalog

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogCategoryBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogRecipeBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogRecipeFavoriteBinding
import io.vasilenko.myrecipes.presentation.common.BaseDiffUtilItemCallback
import io.vasilenko.myrecipes.presentation.common.ListItem
import io.vasilenko.myrecipes.presentation.model.CatalogCategoryItem
import io.vasilenko.myrecipes.presentation.model.CatalogFavoriteRecipeItem
import io.vasilenko.myrecipes.presentation.model.CatalogRecipeItem

class CatalogItemsAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager
            .addDelegate(favoriteRecipesDelegate())
            .addDelegate(categoriesDelegate())
            .addDelegate(recipesDelegate())
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
                binding.imageView.setBackgroundColor(item.hashCode())
                binding.executePendingBindings()
            }
        }

    private fun recipesDelegate() =
        adapterDelegateViewBinding<CatalogRecipeItem, ListItem, ItemCatalogRecipeBinding>(
            { inflater, container -> ItemCatalogRecipeBinding.inflate(inflater, container, false) }
        ) {
            bind {
                binding.title = item.title
                binding.imageView.setBackgroundColor(item.hashCode())
                binding.executePendingBindings()
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
                binding.imageView.setBackgroundColor(item.hashCode())
                binding.executePendingBindings()
            }
        }
}