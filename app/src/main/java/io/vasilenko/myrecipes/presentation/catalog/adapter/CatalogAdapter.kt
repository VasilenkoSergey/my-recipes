package io.vasilenko.myrecipes.presentation.catalog.adapter

import android.view.View.GONE
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogEmptyBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogVerticalBinding
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogGroupEmptyModel
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogGroupModel
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogVerticalGroupModel
import io.vasilenko.myrecipes.presentation.common.BaseDiffUtilItemCallback
import io.vasilenko.myrecipes.presentation.common.ListItem

class CatalogAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(catalogHorizontalDelegate())
        delegatesManager.addDelegate(catalogVerticalDelegate())
        delegatesManager.addDelegate(emptyCatalogDelegate())
    }

    private fun catalogHorizontalDelegate() =
        adapterDelegateViewBinding<CatalogGroupModel, ListItem, ItemCatalogBinding>(
            { layoutInflater, root -> ItemCatalogBinding.inflate(layoutInflater, root, false) }
        ) {
            val adapter = CatalogHorizontalAdapter()
            binding.recyclerView.adapter = adapter

            bind {
                binding.titleTextView.text = item.title
                adapter.items = item.recipes
            }
        }

    private fun catalogVerticalDelegate() =
        adapterDelegateViewBinding<CatalogVerticalGroupModel, ListItem, ItemCatalogVerticalBinding>(
            { layoutInflater, root ->
                ItemCatalogVerticalBinding.inflate(
                    layoutInflater,
                    root,
                    false
                )
            }
        ) {
            val adapter = CatalogVerticalAdapter()
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