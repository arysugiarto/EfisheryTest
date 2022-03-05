package com.efishery.test.util

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.launch

// Todo @Bagus : Fix Lifecycle UseCase

inline fun Fragment.requestPermission(permission: String, crossinline action: (hasPermission: Boolean) -> Unit) {
    val result = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        action.invoke(result)
    }

    result.launch(permission)
}

inline fun Fragment.requestPermission(
    vararg permission: String,
    crossinline action: (permissionResult: Map<String,Boolean>) -> Unit
) = lifecycleScope.launch {
    whenStarted {
        val result = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            action.invoke(result)
        }

        whenCreated {
            result.launch(permission)
        }
    }
}

fun Fragment.hasPermissionOf(
    vararg permissions: String,
    onGrantedAction: (Map<String,Boolean>) -> Unit
): Boolean {
    val context = context ?: return false
    var isAllGranted = true
    val grantSequence = mutableMapOf<String, Boolean>()

    for(permission in permissions) {
        val isGranted = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        grantSequence[permission] = isGranted

        if (!isGranted) isAllGranted = false
        if (permissions.indexOf(permission) == permissions.lastIndex) onGrantedAction.invoke(grantSequence)
    }

    return isAllGranted
}

fun Fragment.hasPermission(permission: String) =
    ContextCompat.checkSelfPermission(requireContext(), permission)

// Todo @Bagus : Add FileProvider Depend on Api level
fun Fragment.takePicture(onTaken: (Boolean, Uri?) -> Unit) {
    val uri: Uri? = null

    val result = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        onTaken.invoke(it, uri)
    }

    result.launch(uri)
}

fun Fragment.takePicture(onTaken: (Bitmap) -> Unit) {
    val result = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        onTaken.invoke(it)
    }

    result.launch(null)
}

fun Fragment.getContent(
        contentType: ContentType,
        action: (Uri) -> Unit
) {
    val result = registerForActivityResult(ActivityResultContracts.GetContent()) {
        action.invoke(it)
    }

    result.launch(when(contentType) {
        ContentType.DOCUMENT -> contentType.content
        ContentType.IMAGE -> contentType.content
        ContentType.PDF -> contentType.content
    })
}

fun Fragment.getContentOf(
        contentType: ContentType,
        action: (List<Uri>) -> Unit
) {
    val result = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
        action.invoke(it)
    }

    result.launch(when(contentType) {
        ContentType.DOCUMENT -> contentType.content
        ContentType.IMAGE -> contentType.content
        ContentType.PDF -> contentType.content
    })
}

enum class ContentType(val content: String) {
    IMAGE("image/*"),
    DOCUMENT("document/*"),
    PDF("pdf/*")
}