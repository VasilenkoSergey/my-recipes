package io.vasilenko.myrecipes.domain.usecase

import io.vasilenko.myrecipes.core.files.FileManager
import java.io.File
import javax.inject.Inject

class CreateImageUseCase @Inject constructor(private val fileManager: FileManager) : UseCase {

    fun execute(): File {
        return fileManager.createTempImageFile()
    }
}