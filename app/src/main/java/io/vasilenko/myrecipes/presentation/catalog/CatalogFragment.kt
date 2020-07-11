package io.vasilenko.myrecipes.presentation.catalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.FragmentCatalogBinding
import io.vasilenko.myrecipes.presentation.common.viewBinding

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private val binding by viewBinding { FragmentCatalogBinding.bind(it) }
    private val viewModel by viewModels<CatalogViewModel>()
    private val adapter = CatalogAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerView.adapter = adapter

            viewModel.data.observe(viewLifecycleOwner, Observer {
                adapter.items = it
            })
        }
    }
}
