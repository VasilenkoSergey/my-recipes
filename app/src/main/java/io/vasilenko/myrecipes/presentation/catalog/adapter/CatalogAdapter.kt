package io.vasilenko.myrecipes.presentation.catalog.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogBinding
import io.vasilenko.myrecipes.presentation.common.BaseDiffUtilItemCallback
import io.vasilenko.myrecipes.presentation.common.ListItem

class CatalogAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(homeRecipesDelegate())
    }

    private fun homeRecipesDelegate() =
        adapterDelegateViewBinding<CatalogGroupItem, ListItem, ItemCatalogBinding>(
            { layoutInflater, root -> ItemCatalogBinding.inflate(layoutInflater, root, false) }
        ) {
            val adapter = CatalogItemsAdapter()
            binding.recyclerView.adapter = adapter

            bind {
                binding.titleTextView.text = item.title
                adapter.items = item.recipes
            }
        }
}