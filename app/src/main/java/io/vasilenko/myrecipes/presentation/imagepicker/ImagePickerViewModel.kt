package io.vasilenko.myrecipes.presentation.imagepicker

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.vasilenko.myrecipes.core.SingleLiveEvent
import io.vasilenko.myrecipes.domain.usecase.CreateImageUseCase
import io.vasilenko.myrecipes.domain.usecase.DeleteImageUseCase
import io.vasilenko.myrecipes.domain.usecase.GetImageDataUseCase
import javax.inject.Inject

class ImagePickerViewModel @Inject constructor(
    private val createImageUseCase: CreateImageUseCase,
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
            createImageUseCase.createImageFile().apply {
                imagePath = absolutePath
                _pickImage.postValue(true)
            }
        } else {
            _pickImage.postValue(true)
            imagePath = path
        }
    }

    fun onImagePicked(data: Uri?) {
        createImageUseCase.saveImage(imagePath, data)
        _close.postValue(imagePath)
    }

    fun onImageCapture(path: String) {
        if (path.isEmpty()) {
            createImageUseCase.createImageFile().apply {
                val uri = getImageDataUseCase.perform(absolutePath)
                _captureImage.postValue(uri)
                imagePath = absolutePath
            }
        } else {
            _captureImage.postValue(getImageDataUseCase.perform(path))
            imagePath = path
        }
    }

    fun onImageCaptured() {
        _close.postValue(imagePath)
    }

    fun onDeleteImage(path: String) {
        if (path.isNotEmpty()) {
            deleteImageUseCase.perform(path)
            _close.postValue("")
        }
    }
}