package io.vasilenko.myrecipes.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.ActivityMainBinding
import io.vasilenko.myrecipes.presentation.common.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val navGraphIds = listOf(
            R.navigation.catalog_navigation,
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
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun setupBottomNavigationBarVisibility(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("MainActivity", destination.id.toString())
            when (destination.id) {
                R.id.navCatalog -> showBottomNav()
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
}
