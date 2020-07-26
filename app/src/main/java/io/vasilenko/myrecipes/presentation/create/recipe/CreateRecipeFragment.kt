package io.vasilenko.myrecipes.presentation.create.recipe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.FragmentCreateRecipeBinding
import io.vasilenko.myrecipes.di.component.CreateRecipeComponent
import io.vasilenko.myrecipes.presentation.common.viewBinding
import io.vasilenko.myrecipes.presentation.model.RecipeModel

class CreateRecipeFragment : Fragment(R.layout.fragment_create_recipe) {

    private val binding by viewBinding { FragmentCreateRecipeBinding.bind(it) }
    private val component by lazy { CreateRecipeComponent.create() }
    private val viewModel by viewModels<CreateRecipeViewModel> { component.viewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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