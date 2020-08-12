package io.vasilenko.myrecipes.presentation.catalog.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogEmptyBinding
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogGroupEmptyModel
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogGroupModel
import io.vasilenko.myrecipes.presentation.common.BaseDiffUtilItemCallback
import io.vasilenko.myrecipes.presentation.common.ListItem

class CatalogAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(catalogDelegate())
        delegatesManager.addDelegate(emptyCatalogDelegate())
    }

    private fun catalogDelegate() =
        adapterDelegateViewBinding<CatalogGroupModel, ListItem, ItemCatalogBinding>(
            { layoutInflater, root -> ItemCatalogBinding.inflate(layoutInflater, root, false) }
        ) {
            val adapter = CatalogItemsAdapter()
            binding.recyclerView.adapter = adapter

            bind {
                binding.titleTextView.text = item.title
                adapter.items = item.recipes
            }
        }

    private fun emptyCatalogDelegate() =
        adapterDelegateViewBinding<CatalogGroupEmptyModel, ListItem, ItemCatalogEmptyBinding>(
            { layoutInflater, root -> ItemCatalogEmptyBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                binding.titleTextView.text = item.title
            }
        }
}