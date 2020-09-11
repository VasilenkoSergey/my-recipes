package io.vasilenko.myrecipes.presentation.creation.category.model

data class CategoryCreationModel(
    val id: Long = 0,
    val title: String,
    val image: String
) {
    override fun toString(): String {
        return title
    }
}