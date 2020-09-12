package io.vasilenko.myrecipes.presentation.catalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.core.presentation.fragment.viewBinding
import io.vasilenko.myrecipes.databinding.FragmentCatalogBinding
import io.vasilenko.myrecipes.di.component.CatalogComponent
import io.vasilenko.myrecipes.presentation.catalog.CatalogFragmentDirections.Companion.actionNavCatalogToRecipeDetails
import io.vasilenko.myrecipes.presentation.catalog.adapter.CatalogAdapter

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private val binding by viewBinding { FragmentCatalogBinding.bind(it) }
    private val component by lazy { CatalogComponent.create() }
    private val viewModel by viewModels<CatalogViewModel> { component.viewModelFactory() }
    private val adapter = CatalogAdapter { recipeId -> recipeDetailsClickListener(recipeId) }
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setupView()
    }

    private fun setupView() {
        viewModel.catalog.observe(viewLifecycleOwner, Observer {
            adapter.items = it
        })
        setupRecycler()
    }

    private fun setupRecycler() {
        binding.recyclerView.adapter = adapter
    }

    private fun recipeDetailsClickListener(recipeId: Long) {
        val action = actionNavCatalogToRecipeDetails(recipeId)
        navController.navigate(action)
    }
}
