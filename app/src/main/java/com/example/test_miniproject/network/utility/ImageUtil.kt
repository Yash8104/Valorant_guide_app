package com.example.test_miniproject.network.utility

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import java.io.OutputStream


fun saveBitmapToGallery(context: Context, bitmap: Bitmap) {
    val contentResolver: ContentResolver = context.contentResolver
    val values = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "downloaded_image_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
    }

    val uri: Uri? = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    uri?.let {
        try {
            val outputStream: OutputStream? = contentResolver.openOutputStream(it)
            if (outputStream != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }
            outputStream?.close()
            Toast.makeText(context, "Player card saved to gallery", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Failed to save the player card", Toast.LENGTH_SHORT).show()
        }
    }
}
