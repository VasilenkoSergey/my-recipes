package io.vasilenko.myrecipes.domain.usecase

import io.vasilenko.myrecipes.data.repo.CategoriesRepository
import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import javax.inject.Inject

class LoadAllCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) : UseCase {

    suspend fun execute(): List<CategoryEntity> {
        return repository.findAll()
    }
}