package io.vasilenko.myrecipes.domain.usecase

import android.net.Uri
import io.vasilenko.myrecipes.core.files.FileManager
import javax.inject.Inject

class GetImageDataUseCase @Inject constructor(private val fileManager: FileManager) : UseCase {

    fun execute(path: String): Uri {
        return fileManager.getImageFileUri(path)
    }
}