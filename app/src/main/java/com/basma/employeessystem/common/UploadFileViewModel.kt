package com.basma.employeessystem.common

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.util.*


interface UploadFileViewModel {
    var fileUri: Uri?
    val notifyFileInserted : SingleLiveEvent<Void>

    fun onFileSelected() {
        notifyFileInserted.call()
    }

    fun setFileDetails(uri: Uri) {
        fileUri = uri
    }

    fun createImageFile(context: Context): File {
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile: File
        try {
            imageFile = File.createTempFile(generateFilename(), ".jpg", storageDir)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        return imageFile
    }

    fun createURI(context: Context): Uri {
        val authority = context.applicationContext.packageName + ".provider"
        val imageFile = createImageFile(context)
        fileUri = FileProvider.getUriForFile(context, authority, imageFile)
        return fileUri!!
    }

    private fun generateFilename() = "image-" + Calendar.getInstance().timeInMillis
}