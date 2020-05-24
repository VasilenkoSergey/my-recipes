package io.vasilenko.myrecipes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.databinding.ActivityMainBinding
import io.vasilenko.myrecipes.presentation.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, HomeFragment())
                .commitAllowingStateLoss()
        }
    }
}
