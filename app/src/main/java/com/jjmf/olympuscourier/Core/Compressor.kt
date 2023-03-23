package com.jjmf.olympuscourier.Core

import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import id.zelory.compressor.Compressor
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class Compressor {

    suspend fun compressImage(context: Context, uri: Uri, namePhoto: String): File {
        val file = getFile(context, uri, namePhoto)
        val compressor = Compressor.compress(context, file)
        file.delete()
        return compressor
    }

    private fun getFile(context: Context, uri: Uri?, namePhoto: String): File {
        val dir = ContextWrapper(context).getDir("photo", Context.MODE_PRIVATE)
        val destinationFilename = File(dir, "$namePhoto.jpg")
        context.contentResolver.openInputStream(uri!!).use { inputStream ->
            createFileFromStream(inputStream!!, destinationFilename)
        }
        return destinationFilename
    }

    private fun createFileFromStream(ins: InputStream, destination: File?) {
        val fl = FileOutputStream(destination)
        val buffer = ByteArray(4096)
        var length: Int
        while (ins.read(buffer).also { length = it } > 0) {
            fl.write(buffer, 0, length)
        }
        fl.flush()
    }
}