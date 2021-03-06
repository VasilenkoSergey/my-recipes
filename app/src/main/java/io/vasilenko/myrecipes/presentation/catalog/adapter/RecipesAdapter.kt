package io.vasilenko.myrecipes.presentation.catalog.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.vasilenko.myrecipes.core.ext.load
import io.vasilenko.myrecipes.databinding.ItemCatalogRecipeBinding
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogRecipeModel
import io.vasilenko.myrecipes.core.presentation.adapter.BaseDiffUtilItemCallback
import io.vasilenko.myrecipes.core.presentation.adapter.ListItem

class RecipesAdapter(
    private val clickListener: (Long) -> Unit
) : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(recipeItemsDelegate())
    }

    private fun recipeItemsDelegate() =
        adapterDelegateViewBinding<CatalogRecipeModel, ListItem, ItemCatalogRecipeBinding>(
            { inflater, container ->
                ItemCatalogRecipeBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                binding.title = item.title
                binding.imageView.load(item.image)
                binding.executePendingBindings()
            }
            itemView.setOnClickListener { clickListener(item.id) }
        }
}