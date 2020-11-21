package io.vasilenko.myrecipes.domain.usecase

import io.vasilenko.myrecipes.core.files.FileManager
import javax.inject.Inject

class DeleteImageUseCase @Inject constructor(private val fileManager: FileManager) : UseCase {

    fun execute(path: String) {
        fileManager.deleteImageFile(path)
    }
}