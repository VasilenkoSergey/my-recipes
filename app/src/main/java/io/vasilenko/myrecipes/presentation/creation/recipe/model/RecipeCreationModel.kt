package io.vasilenko.myrecipes.presentation.creation.recipe.model

data class RecipeCreationModel(
    val id: Long = 0,
    val title: String,
    val image: String = "",
    val categoryId: Long?
)