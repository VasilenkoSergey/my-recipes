package io.vasilenko.myrecipes.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.core.ext.disableTooltipText
import io.vasilenko.myrecipes.core.presentation.navigation.setupWithNavController
import io.vasilenko.myrecipes.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null
    private var doubleBackToExitPressedOnce = false
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigationView = binding.bottomNavigation
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.catalog_navigation,
            R.navigation.favorite_navigation,
            R.navigation.creation_navigation,
            R.navigation.basket_navigation,
            R.navigation.more_navigation
        )
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragmentContainer,
            intent = intent
        )
        controller.observe(this, Observer {
            setupBottomNavigationBarVisibility(it)
        })

        currentNavController = controller
        bottomNavigationView.disableTooltipText()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun setupBottomNavigationBarVisibility(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navCatalog -> showBottomNav()
                R.id.navFavorite -> showBottomNav()
                R.id.navCreation -> showBottomNav()
                R.id.navBasket -> showBottomNav()
                R.id.navMore -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun hideBottomNav() {
        bottomNavigation.visibility = View.GONE

    }

    private fun showBottomNav() {
        bottomNavigation.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        val start = findNavController(this, R.id.fragmentContainer).currentDestination?.id
        if (start == R.id.navCatalog) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }
            doubleBackToExitPressedOnce = true
            Toast.makeText(this, R.string.double_back_exit, Toast.LENGTH_SHORT).show()
            lifecycleScope.launch {
                delay(2000)
                doubleBackToExitPressedOnce = false
            }
        } else {
            super.onBackPressed()
        }
    }
}
