package io.vasilenko.myrecipes.presentation.model

data class CategoryModel(
    val id: Long = 0,
    val title: String,
    val image: String
){
    override fun toString(): String {
        return title
    }
}