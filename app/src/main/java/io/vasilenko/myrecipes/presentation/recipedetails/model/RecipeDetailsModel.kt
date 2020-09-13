package io.vasilenko.myrecipes.presentation.recipedetails.model

data class RecipeDetailsModel(
    val id: Long,
    val title: String,
    val image: String = ""
)