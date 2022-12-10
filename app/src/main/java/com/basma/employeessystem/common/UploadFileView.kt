package com.basma.employeessystem.common

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


interface UploadFileView {
    val uploadFileActivity: AppCompatActivity
        get() = this as AppCompatActivity

    val uploadFileViewModel: UploadFileViewModel

    fun readPickedFile(data: Intent?) {
        data?.data?.let { uploadFileViewModel.setFileDetails(it) }
        uploadFileViewModel.onFileSelected()
    }

    fun initializeFilePicker(cameraUri: Uri) {
        val fileIntent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(Intent.EXTRA_MIME_TYPES, "image/*")
            putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        }
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply { putExtra(MediaStore.EXTRA_OUTPUT, cameraUri) }
        val chooserIntent = Intent.createChooser(fileIntent, "Open with").apply {
            putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        }

        if (cameraIntent.resolveActivity(uploadFileActivity.packageManager) != null && fileIntent.resolveActivity(uploadFileActivity.packageManager) != null) {
            uploadFileActivity.startActivityForResult(chooserIntent, READ_REQUEST_CODE)
        } else {
            Toast.makeText(uploadFileActivity, "Can't upload file", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val READ_REQUEST_CODE = 2
    }
}
