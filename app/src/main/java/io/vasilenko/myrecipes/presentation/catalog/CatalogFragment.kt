package io.vasilenko.myrecipes.presentation.catalog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.getbase.floatingactionbutton.FloatingActionsMenu.OnFloatingActionsMenuUpdateListener
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
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setupView()
    }

    override fun onResume() {
        super.onResume()
        binding.fab.collapse()
    }

    private fun setupView() {
        viewModel.catalog.observe(viewLifecycleOwner, Observer {
            adapter.items = it
        })
        setupFab()
        setupRecycler()
        setupViewTouch()
    }

    private fun setupRecycler() {
        with(binding) {
            recyclerView.adapter = adapter

            recyclerView.addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0 && fab.visibility == View.VISIBLE) {
                        fab.visibility = View.INVISIBLE
                    } else if (dy < 0 && fab.visibility != View.VISIBLE) {
                        fab.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    private fun setupFab() {
        with(binding) {
            fabRecipe.setOnClickListener {
                navController.navigate(R.id.action_navCatalog_to_recipeCreationFragment)
            }

            fabCategory.setOnClickListener {
                navController.navigate(R.id.action_navCatalog_to_categoryCreationFragment)
            }

            fab.setOnFloatingActionsMenuUpdateListener(object :
                OnFloatingActionsMenuUpdateListener {

                override fun onMenuCollapsed() {
                    dimLayout(false)
                }

                override fun onMenuExpanded() {
                    dimLayout(true)
                }
            })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupViewTouch() {
        with(binding) {
            dummyFrame.setOnTouchListener(View.OnTouchListener { _, _ ->
                if (fab.isExpanded) {
                    fab.collapse()
                    return@OnTouchListener true
                }
                false
            })
        }
    }

    private fun dimLayout(isDim: Boolean) {
        when {
            isDim -> binding.recyclerView.alpha = DIM
            else -> binding.recyclerView.alpha = NOT_DIM
        }
    }

    companion object {
        const val DIM = 0.5F
        const val NOT_DIM = 1.0F
    }
}
