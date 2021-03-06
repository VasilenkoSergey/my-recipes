package io.vasilenko.myrecipes.domain.entity

data class RecipeEntity(
    val id: Long,
    val name: String,
    val categoryId: Long?,
    val image: String = ""
)