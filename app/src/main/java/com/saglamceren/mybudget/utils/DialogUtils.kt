package com.saglamceren.mybudget.utils

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogUtils {
    fun showWarning(context: Context, title: String = "Uyarı", message: String, posText: String = "Tamam") {
        MaterialAlertDialogBuilder(context).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(posText, null)
            setCancelable(false)
            show()
        }
    }
}