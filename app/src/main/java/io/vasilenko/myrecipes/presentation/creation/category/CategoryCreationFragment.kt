package io.vasilenko.myrecipes.presentation.creation.category

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.FragmentCreationCategoryBinding
import io.vasilenko.myrecipes.di.component.CategoryCreationComponent
import io.vasilenko.myrecipes.core.presentation.fragment.viewBinding
import io.vasilenko.myrecipes.presentation.creation.category.model.CategoryCreationModel

class CategoryCreationFragment : Fragment(R.layout.fragment_creation_category) {

    private val binding by viewBinding { FragmentCreationCategoryBinding.bind(it) }
    private val component by lazy { CategoryCreationComponent.create() }
    private val viewModel by viewModels<CategoryCreationViewModel> { component.viewModelFactory() }

    private lateinit var navController: NavController

    private lateinit var title: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setupView()
        setupBackPress()
    }

    private fun setupView() {
        with(binding) {
            toolbar.title = getString(R.string.create_category_title)
            toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        }

        viewModel.isCreateButtonEnabled.observe(viewLifecycleOwner, Observer {
            setCreateButtonAccess(it)
        })

        val createBtn = binding.createBtn
        createBtn.setOnClickListener {
            viewModel.saveCategory(CategoryCreationModel(title = title, image = ""))
            close()
        }
        binding.nameEditText.doAfterTextChanged {
            val text = it.toString()
            title = text
            viewModel.afterNameTextChanged(text)
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
                    navController.navigate(R.id.action_categoryCreationFragment_to_creation_cancel_dialog)
                }
            })

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(CANCELLED_FLAG)
            ?.observe(viewLifecycleOwner, Observer { isCancelled -> if (isCancelled) close() })
    }

    private fun close() {
        navController.popBackStack()
    }

    companion object {
        const val CANCELLED_FLAG = "is_cancelled"
    }
}