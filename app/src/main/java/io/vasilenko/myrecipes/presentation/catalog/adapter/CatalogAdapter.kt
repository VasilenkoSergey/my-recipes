package io.vasilenko.myrecipes.presentation.catalog.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.ItemCatalogCategoriesBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogEmptyBinding
import io.vasilenko.myrecipes.databinding.ItemCatalogRecipesBinding
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogCategoriesGroupModel
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogEmptyGroupModel
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogRecipesGroupModel
import io.vasilenko.myrecipes.presentation.common.BaseDiffUtilItemCallback
import io.vasilenko.myrecipes.presentation.common.ItemDecoration
import io.vasilenko.myrecipes.presentation.common.ListItem


class CatalogAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(categoriesDelegate())
        delegatesManager.addDelegate(recipesDelegate())
        delegatesManager.addDelegate(emptyDelegate())
    }

    private fun categoriesDelegate() =
        adapterDelegateViewBinding<CatalogCategoriesGroupModel, ListItem, ItemCatalogCategoriesBinding>(
            { layoutInflater, root ->
                ItemCatalogCategoriesBinding.inflate(
                    layoutInflater,
                    root,
                    false
                )
            }
        ) {
            val adapter = CategoriesAdapter()
            binding.recyclerView.adapter = adapter

            bind {
                adapter.items = item.recipes
            }
        }

    private fun recipesDelegate() =
        adapterDelegateViewBinding<CatalogRecipesGroupModel, ListItem, ItemCatalogRecipesBinding>(
            { layoutInflater, root ->
                ItemCatalogRecipesBinding.inflate(
                    layoutInflater,
                    root,
                    false
                )
            }
        ) {
            val adapter = RecipesAdapter()
            val padding = binding.recyclerView.context.resources.getDimensionPixelSize(R.dimen.decoration_padding)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.addItemDecoration(
                ItemDecoration(padding)
            )

            bind {
                binding.titleTextView.text = item.title
                adapter.items = item.recipes
            }
        }

    private fun emptyDelegate() =
        adapterDelegateViewBinding<CatalogEmptyGroupModel, ListItem, ItemCatalogEmptyBinding>(
            { layoutInflater, root -> ItemCatalogEmptyBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                binding.titleTextView.text = item.title
            }
        }
}