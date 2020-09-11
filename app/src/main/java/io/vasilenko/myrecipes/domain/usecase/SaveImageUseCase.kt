package io.vasilenko.myrecipes.domain.usecase

import android.net.Uri
import io.vasilenko.myrecipes.core.files.FileManager
import java.io.FileOutputStream
import javax.inject.Inject

class SaveImageUseCase @Inject constructor(private val fileManager: FileManager) : UseCase {

    fun execute(path: String, uri: Uri?) {
        uri?.let {
            FileOutputStream(path).use { outStream ->
                val inStream = fileManager.openContentResolverInputStream(it)
                inStream?.let {
                    outStream.write(it.readBytes())
                    it.close()
                }
                outStream.close()
            }
        }
    }
}