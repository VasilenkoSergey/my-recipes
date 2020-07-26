package io.vasilenko.myrecipes.presentation.creation.recipe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.FragmentCreationRecipeBinding
import io.vasilenko.myrecipes.di.component.RecipeCreationComponent
import io.vasilenko.myrecipes.presentation.common.viewBinding
import io.vasilenko.myrecipes.presentation.model.RecipeModel

class RecipeCreationFragment : Fragment(R.layout.fragment_creation_recipe) {

    private val binding by viewBinding { FragmentCreationRecipeBinding.bind(it) }
    private val component by lazy { RecipeCreationComponent.create() }
    private val viewModel by viewModels<RecipeCreationViewModel> { component.viewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        with(binding) {
            toolbar.title = getString(R.string.create_recipe_title)
            toolbar.setNavigationOnClickListener { close() }
        }

        val createBtn = binding.createBtn
        createBtn.setOnClickListener {
            val title = binding.nameEditText.text.toString()
            title.let {
                viewModel.createRecipe(RecipeModel(title = title, image = ""))
            }
            close()
        }
    }

    private fun close() {
        requireActivity().onBackPressed()
    }
}