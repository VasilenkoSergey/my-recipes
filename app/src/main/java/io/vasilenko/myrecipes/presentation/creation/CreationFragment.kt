package io.vasilenko.myrecipes.presentation.creation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.FragmentCreationBinding
import io.vasilenko.myrecipes.core.presentation.fragment.viewBinding

class CreationFragment : Fragment(R.layout.fragment_creation) {

    private val binding by viewBinding { FragmentCreationBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        with(binding) {
            btnCreateCategory.setOnClickListener {
                navController.navigate(R.id.action_navCreate_to_createCategoryFragment)
            }
            btnCreateRecipe.setOnClickListener {
                navController.navigate(R.id.action_navCreate_to_createRecipeFragment)
            }
        }
    }
}