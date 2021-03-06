package io.vasilenko.myrecipes.presentation.recipedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.core.ext.load
import io.vasilenko.myrecipes.core.presentation.fragment.viewBinding
import io.vasilenko.myrecipes.databinding.FragmentRecipeDetailsBinding
import io.vasilenko.myrecipes.di.component.RecipeDetailsComponent

class RecipeDetailsFragment : Fragment(R.layout.fragment_recipe_details) {

    private val binding by viewBinding { FragmentRecipeDetailsBinding.bind(it) }
    private val component by lazy { RecipeDetailsComponent.create() }
    private val viewModel by viewModels<RecipeDetailsViewModel> { component.viewModelFactory() }
    private val args: RecipeDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.toolbar.inflateMenu(R.menu.menu_recipe_details)
        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        viewModel.onViewCreated(args.id)
        viewModel.recipeDetails.observe(viewLifecycleOwner, Observer {
            binding.image.load(it.image)
            binding.toolbar.title = it.title
        })
    }
}