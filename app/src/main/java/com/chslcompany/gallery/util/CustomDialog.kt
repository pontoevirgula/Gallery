package com.chslcompany.gallery.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.gallery.databinding.CustomDialogFragmentBinding
import com.chslcompany.gallery.ui.extensions.loadBlurredImageWithPlaceholder
import com.google.android.material.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CustomDialog(
    private val photoDomain: PhotoDomain,
    private val clickListener: () -> Unit
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = CustomDialogFragmentBinding.inflate(layoutInflater)
        binding.run {
            btnNo.setOnClickListener {
                dismiss()
            }
            btnYes.setOnClickListener {
                clickListener.invoke()
                dismiss()
            }
            imageDelete.loadBlurredImageWithPlaceholder(
                photoDomain.srcDomain?.small,
                photoDomain.avgColor
            )
        }
        return MaterialAlertDialogBuilder(
            requireContext(),
            R.style.MaterialAlertDialog_Material3
        )
            .setCancelable(false)
            .setView(binding.root)
            .create().apply {
                window?.setBackgroundDrawable(
                    ColorDrawable(Color.TRANSPARENT)
                )
            }
    }

}