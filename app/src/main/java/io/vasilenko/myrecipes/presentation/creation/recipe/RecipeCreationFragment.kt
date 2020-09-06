package io.vasilenko.myrecipes.presentation.creation.recipe

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.core.ext.loadSearched
import io.vasilenko.myrecipes.databinding.FragmentCreationRecipeBinding
import io.vasilenko.myrecipes.di.component.RecipeCreationComponent
import io.vasilenko.myrecipes.presentation.common.viewBinding
import io.vasilenko.myrecipes.presentation.model.CategoryModel
import io.vasilenko.myrecipes.presentation.model.RecipeModel
import io.vasilenko.myrecipes.presentation.creation.recipe.RecipeCreationFragmentDirections.Companion.actionRecipeCreationFragmentToImagePickerDialog as imagePickerAction

class RecipeCreationFragment : Fragment(R.layout.fragment_creation_recipe) {

    private val binding by viewBinding { FragmentCreationRecipeBinding.bind(it) }
    private val component by lazy { RecipeCreationComponent.create() }
    private val viewModel by viewModels<RecipeCreationViewModel> { component.viewModelFactory() }

    private lateinit var navController: NavController

    private lateinit var title: String
    private var categoryId: Long? = null
    private var imagePath: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setupView()
        setupImage()
        setupBackPress()
    }

    private fun setupView() {
        with(binding) {
            toolbar.title = getString(R.string.create_recipe_title)
            toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        }

        viewModel.isCreateButtonEnabled.observe(viewLifecycleOwner, Observer {
            setCreateButtonAccess(it)
        })

        viewModel.categories.observe(viewLifecycleOwner, Observer {
            val adapter = ArrayAdapter(requireContext(), R.layout.item_category_dropdown, it)
            binding.categoryEditText.setAdapter(adapter)
            binding.categoryEditText.onItemClickListener =
                AdapterView.OnItemClickListener { parent, _, position, _ ->
                    val category = parent.getItemAtPosition(position) as CategoryModel
                    categoryId = category.id
                }
        })

        val createBtn = binding.createBtn
        createBtn.setOnClickListener {
            viewModel.createRecipe(
                RecipeModel(
                    title = title,
                    image = imagePath,
                    categoryId = categoryId
                )
            )
            close()
        }

        binding.nameEditText.doAfterTextChanged {
            val text = it.toString()
            title = text
            viewModel.afterTitleTextChanged(text)
        }
    }

    private fun setCreateButtonAccess(isEnabled: Boolean) {
        binding.createBtn.isEnabled = isEnabled
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
            ?.observe(viewLifecycleOwner, Observer { isCancelled -> if (isCancelled) cancel() })
    }

    private fun setupImage() {
        binding.imageView.setOnClickListener {
            val action = imagePickerAction(imagePath)
            navController.navigate(action)
        }

        val savedState = navController.currentBackStackEntry?.savedStateHandle
        savedState?.getLiveData<String>(IMAGE_URI)?.observe(viewLifecycleOwner, Observer {
            loadImage(it)
        })
    }

    private fun loadImage(uri: String) {
        binding.imageView.loadSearched(uri)
        imagePath = uri
    }

    private fun cancel() {
        viewModel.onCancel(imagePath)
        close()
    }

    private fun close() {
        navController.popBackStack()
    }

    companion object {
        const val CANCELLED_FLAG = "is_cancelled"
        const val IMAGE_URI = "img_uri"
    }
}