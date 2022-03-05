package com.efishery.test.util

import android.content.Context

/**
 * @author [Shidiq Bagus Ardi Prastian]
 * Return Application Version number of string type
 */
val Context?.appVersionNumber: String get() {
    return try {
        this?.applicationContext?.let {
            val packageInfo = it.packageManager.getPackageInfo(packageName, 0)
            packageInfo.versionName
        }.orEmpty
    } catch (e: Exception) {
        "Version Mismatch" // Also Can Assigned to Null
    }
}