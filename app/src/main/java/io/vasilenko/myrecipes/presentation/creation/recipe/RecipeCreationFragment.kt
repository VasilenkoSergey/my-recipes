package io.vasilenko.myrecipes.presentation.creation.recipe

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.FragmentCreationRecipeBinding
import io.vasilenko.myrecipes.di.component.RecipeCreationComponent
import io.vasilenko.myrecipes.presentation.common.viewBinding
import io.vasilenko.myrecipes.presentation.model.RecipeModel

class RecipeCreationFragment : Fragment(R.layout.fragment_creation_recipe) {

    private val binding by viewBinding { FragmentCreationRecipeBinding.bind(it) }
    private val component by lazy { RecipeCreationComponent.create() }
    private val viewModel by viewModels<RecipeCreationViewModel> { component.viewModelFactory() }

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setupView()
        setupBackPress()
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

    private fun setupBackPress() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.navigate(R.id.action_recipeCreationFragment_to_creation_cancel_dialog)
                }
            })

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(CANCELLED_FLAG)
            ?.observe(viewLifecycleOwner, Observer { if (it) close() })
    }

    private fun close() {
        navController.popBackStack()
    }

    companion object {
        const val CANCELLED_FLAG = "is_cancelled"
    }
}