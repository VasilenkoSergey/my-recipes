package io.vasilenko.myrecipes.presentation.creation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import io.vasilenko.myrecipes.databinding.DialogCreationCancelBinding

class CreationCancelDialogFragment : DialogFragment() {

    private var _binding: DialogCreationCancelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCreationCancelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val okBtn = binding.backConfirm
        val cancelBtn = binding.backCancel
        okBtn.setOnClickListener { close(isExitConfirmed = true) }
        cancelBtn.setOnClickListener { close(isExitConfirmed = false) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun close(isExitConfirmed: Boolean) {
        val navController = findNavController()
        if (isExitConfirmed) {
            navController.previousBackStackEntry?.savedStateHandle?.set(CANCELLED_FLAG, true)
        }
        navController.popBackStack()
    }

    companion object {
        const val CANCELLED_FLAG = "is_cancelled"
    }
}