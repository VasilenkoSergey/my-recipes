package io.vasilenko.myrecipes.domain.usecase

import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.domain.repo.CategoriesRepo
import javax.inject.Inject

class CreateCategoryUseCase @Inject constructor(private val repository: CategoriesRepo) : UseCase {

    suspend fun execute(categoryEntity: CategoryEntity) {
        repository.save(categoryEntity)
    }
}