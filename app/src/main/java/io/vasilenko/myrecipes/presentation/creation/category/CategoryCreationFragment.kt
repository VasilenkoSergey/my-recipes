package io.vasilenko.myrecipes.presentation.creation.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.FragmentCreationCategoryBinding
import io.vasilenko.myrecipes.di.component.CategoryCreationComponent
import io.vasilenko.myrecipes.presentation.common.viewBinding
import io.vasilenko.myrecipes.presentation.model.CategoryModel

class CategoryCreationFragment : Fragment(R.layout.fragment_creation_category) {

    private val binding by viewBinding { FragmentCreationCategoryBinding.bind(it) }
    private val component by lazy { CategoryCreationComponent.create() }
    private val viewModel by viewModels<CategoryCreationViewModel> { component.viewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        with(binding) {
            toolbar.title = getString(R.string.create_category_title)
            toolbar.setNavigationOnClickListener { close() }
        }

        val createBtn = binding.createBtn
        createBtn.setOnClickListener {
            val title = binding.nameEditText.text.toString()
            title.let {
                viewModel.saveCategory(CategoryModel(title = title, image = ""))
            }
            close()
        }
    }

    private fun close() {
        requireActivity().onBackPressed()
    }
}