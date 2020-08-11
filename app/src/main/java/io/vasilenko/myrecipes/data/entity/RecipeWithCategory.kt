package io.vasilenko.myrecipes.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithCategory(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: Category?
)