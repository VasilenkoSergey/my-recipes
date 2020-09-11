package io.vasilenko.myrecipes.presentation.imagepicker

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.vasilenko.myrecipes.core.presentation.viewmodel.SingleLiveEvent
import io.vasilenko.myrecipes.domain.usecase.CreateImageUseCase
import io.vasilenko.myrecipes.domain.usecase.DeleteImageUseCase
import io.vasilenko.myrecipes.domain.usecase.GetImageDataUseCase
import io.vasilenko.myrecipes.domain.usecase.SaveImageUseCase
import javax.inject.Inject

class ImagePickerViewModel @Inject constructor(
    private val createImageUseCase: CreateImageUseCase,
    private val saveImageUseCase: SaveImageUseCase,
    private val getImageDataUseCase: GetImageDataUseCase,
    private val deleteImageUseCase: DeleteImageUseCase
) : ViewModel() {

    private lateinit var imagePath: String

    private val _close: SingleLiveEvent<String> = SingleLiveEvent()
    val close: LiveData<String> get() = _close

    private val _pickImage: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val pickImage: LiveData<Boolean> get() = _pickImage

    private val _captureImage: SingleLiveEvent<Uri> = SingleLiveEvent()
    val captureImage: LiveData<Uri> get() = _captureImage

    fun onImagePick(path: String) {
        if (path.isEmpty()) {
            createImageUseCase.execute().apply {
                imagePath = absolutePath
                _pickImage.postValue(true)
            }
        } else {
            _pickImage.postValue(true)
            imagePath = path
        }
    }

    fun onImagePicked(data: Uri?) {
        saveImageUseCase.execute(imagePath, data)
        _close.postValue(imagePath)
    }

    fun onImageCapture(path: String) {
        if (path.isEmpty()) {
            createImageUseCase.execute().apply {
                val uri = getImageDataUseCase.execute(absolutePath)
                _captureImage.postValue(uri)
                imagePath = absolutePath
            }
        } else {
            _captureImage.postValue(getImageDataUseCase.execute(path))
            imagePath = path
        }
    }

    fun onImageCaptured() {
        _close.postValue(imagePath)
    }

    fun onDeleteImage(path: String) {
        if (path.isNotEmpty()) {
            deleteImageUseCase.execute(path)
            _close.postValue("")
        }
    }
}