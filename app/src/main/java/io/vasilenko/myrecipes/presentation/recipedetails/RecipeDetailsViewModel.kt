package io.vasilenko.myrecipes.presentation.recipedetails

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor() : ViewModel() {

    fun temp(recipeId: Long) {
        Log.i("RecipeDetailsViewModel", recipeId.toString())
    }
}