package io.vasilenko.myrecipes.presentation.imagepicker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.vasilenko.myrecipes.core.presentation.fragment.AppBottomSheetDialogFragment
import io.vasilenko.myrecipes.databinding.DialogImagePickerBinding
import io.vasilenko.myrecipes.di.component.ImagePickerComponent

class ImagePickerDialog : AppBottomSheetDialogFragment() {

    private var _binding: DialogImagePickerBinding? = null
    private val binding get() = _binding!!

    private val component by lazy { ImagePickerComponent.create() }
    private val viewModel by viewModels<ImagePickerViewModel> { component.viewModelFactory() }

    private val args: ImagePickerDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogImagePickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.galleryTextView.setOnClickListener { viewModel.onImagePick(args.path) }
        binding.cameraTextView.setOnClickListener { viewModel.onImageCapture(args.path) }
        binding.deleteTextView.setOnClickListener { viewModel.onDeleteImage(args.path) }

        viewModel.pickImage.observe(viewLifecycleOwner, Observer { if (it) pickImage() })
        viewModel.captureImage.observe(viewLifecycleOwner, Observer { captureImage(it) })
        viewModel.close.observe(viewLifecycleOwner, Observer { close(it) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = FILES_TYPE
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    private fun captureImage(uri: Uri) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        when (requestCode) {
            REQUEST_IMAGE_PICK -> {
                if (resultCode == Activity.RESULT_OK) {
                    viewModel.onImagePicked(intent?.data)
                }
            }
            REQUEST_IMAGE_CAPTURE -> {
                if (resultCode == Activity.RESULT_OK) {
                    viewModel.onImageCaptured()
                }
            }
        }
    }

    private fun close(path: String) {
        val navController = findNavController()
        val savedState = navController.previousBackStackEntry?.savedStateHandle
        savedState?.set(IMAGE_URI, path)
        navController.popBackStack()
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
        const val REQUEST_IMAGE_PICK = 2
        const val IMAGE_URI = "img_uri"
        const val FILES_TYPE = "image/*"
    }
}