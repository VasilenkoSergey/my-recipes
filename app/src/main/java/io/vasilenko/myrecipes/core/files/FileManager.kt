package io.vasilenko.myrecipes.core.files

import android.net.Uri
import java.io.File
import java.io.InputStream

interface FileManager {

    fun createTempImageFile(): File

    fun deleteImageFile(path: String)

    fun openContentResolverInputStream(uri: Uri): InputStream?

    fun getImageFileUri(path: String): Uri
}