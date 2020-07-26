package io.vasilenko.myrecipes.presentation.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.FragmentCategoryBinding
import io.vasilenko.myrecipes.di.component.CategoryComponent
import io.vasilenko.myrecipes.presentation.common.viewBinding
import io.vasilenko.myrecipes.presentation.model.CategoryModel

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private val binding by viewBinding { FragmentCategoryBinding.bind(it) }
    private val component by lazy { CategoryComponent.create() }
    private val viewModel by viewModels<CategoryViewModel> { component.viewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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