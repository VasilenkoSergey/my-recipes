package io.vasilenko.myrecipes.presentation.catalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.FragmentCatalogBinding
import io.vasilenko.myrecipes.di.component.CatalogComponent
import io.vasilenko.myrecipes.presentation.catalog.adapter.CatalogAdapter
import io.vasilenko.myrecipes.presentation.common.viewBinding

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private val binding by viewBinding { FragmentCatalogBinding.bind(it) }
    private val component by lazy { CatalogComponent.create() }
    private val viewModel by viewModels<CatalogViewModel> { component.viewModelFactory() }
    private val adapter = CatalogAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        with(binding) {
            recyclerView.adapter = adapter

            viewModel.catalog.observe(viewLifecycleOwner, Observer {
                adapter.items = it
            })

            fab.setOnClickListener {
                navController.navigate(R.id.action_navCatalog_to_navCreation)
            }
        }
    }
}
