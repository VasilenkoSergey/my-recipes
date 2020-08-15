package io.vasilenko.myrecipes.domain.usecase

import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.domain.repo.CategoriesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadAllCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepo
) : UseCase {

    fun execute(): Flow<List<CategoryEntity>> {
        return repository.findAll()
    }
}