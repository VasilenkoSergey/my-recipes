package io.vasilenko.myrecipes.presentation.home

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.vasilenko.myrecipes.databinding.ItemHomeBinding
import io.vasilenko.myrecipes.databinding.ItemRecipeBigBinding
import io.vasilenko.myrecipes.databinding.ItemRecipeMediumBinding
import io.vasilenko.myrecipes.presentation.common.ListItem
import io.vasilenko.myrecipes.presentation.model.BigRecipeItem
import io.vasilenko.myrecipes.presentation.model.MediumRecipeItem
import io.vasilenko.myrecipes.presentation.model.RecipesGroupItem

object HomeDelegates {

    fun homeRecipesDelegate() =
        adapterDelegateViewBinding<RecipesGroupItem, ListItem, ItemHomeBinding>(
            { layoutInflater, root -> ItemHomeBinding.inflate(layoutInflater, root, false) }
        ) {
            val adapter = RecipesAdapter()
            binding.recyclerView.adapter = adapter

            bind {
                binding.titleTextView.text = item.title
                adapter.items = item.recipes
            }
        }

    fun mediumRecipesDelegate() =
        adapterDelegateViewBinding<MediumRecipeItem, ListItem, ItemRecipeMediumBinding>(
            { inflater, container -> ItemRecipeMediumBinding.inflate(inflater, container, false) }
        ) {
            bind {
                binding.title = item.title
                binding.imageView.setBackgroundColor(item.hashCode())
                binding.executePendingBindings()
            }
        }

    fun bigRecipesDelegate() =
        adapterDelegateViewBinding<BigRecipeItem, ListItem, ItemRecipeBigBinding>(
            { inflater, container -> ItemRecipeBigBinding.inflate(inflater, container, false) }
        ) {
            bind {
                binding.title = item.title
                binding.imageView.setBackgroundColor(item.hashCode())
                binding.executePendingBindings()
            }
        }
}