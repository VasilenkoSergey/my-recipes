package io.vasilenko.myrecipes.presentation.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.FragmentCreateBinding
import io.vasilenko.myrecipes.presentation.common.viewBinding

class CreateFragment : Fragment(R.layout.fragment_create) {

    private val binding by viewBinding { FragmentCreateBinding.bind(it) }

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