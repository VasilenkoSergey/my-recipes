package io.vasilenko.myrecipes.core.files

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AndroidFileManager @Inject constructor(private val context: Context) : FileManager {

    override fun createTempImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat(IMG_DATE_FORMAT, Locale.getDefault()).format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("${IMG_PREFIX}_${timeStamp}_", IMG_EXTENSION, storageDir)
    }

    override fun deleteImageFile(path: String) {
        val file = File(path)
        file.delete()
    }

    override fun openContentResolverInputStream(uri: Uri) =
        context.contentResolver.openInputStream(uri)

    override fun getImageFileUri(path: String): Uri {
        File(path).also {
            return FileProvider.getUriForFile(
                context,
                PROVIDER_AUTH,
                it
            )
        }
    }

    companion object {
        const val IMG_DATE_FORMAT = "yyyyMMdd_HHmmss"
        const val IMG_PREFIX = "JPEG"
        const val IMG_EXTENSION = ".jpg"
        const val PROVIDER_AUTH = "io.vasilenko.myrecipes.fileprovider"
    }
}