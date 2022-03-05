package com.efishery.test.util

import java.io.File
import java.text.SimpleDateFormat
import java.util.*


fun createImageFile(): File {
    val timeStamp = SimpleDateFormat.getDateTimeInstance().format(Date())
    val storageDir = getPicturesDirectory()

    return File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        storageDir
    ).apply {
        val currentImagePath = absolutePath
    }
}

fun getPicturesDirectory(): File? {
    return null
}
