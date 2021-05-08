package com.saglamceren.mybudget.utils

import android.content.Context

class PreferencesUtil(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()


    fun setInfo(name: String, gender: Int) {
        editor.apply {
            putString(NAME, name)
            putInt(GENDER, gender)
            apply()
        }
    }

    fun getName(): String {
        return sharedPreferences.getString(NAME, BLANK) ?: BLANK
    }

    fun getGender(): Int {
        return sharedPreferences.getInt(GENDER, 0)
    }

    fun isFirstEntry(): Boolean {
        return if (sharedPreferences.getBoolean(FIRST_ENTRY, true)) {
            editor.apply {
                putBoolean(FIRST_ENTRY, false)
                commit()
            }
            true
        } else {
            false
        }
    }


    private companion object KEY {
        private const val BLANK = ""
        private const val GENDER = "KEY_GENDER"
        private const val NAME = "KEY_NAME"
        private const val FIRST_ENTRY = "KEY_FIRST_ENTRY"
    }
}