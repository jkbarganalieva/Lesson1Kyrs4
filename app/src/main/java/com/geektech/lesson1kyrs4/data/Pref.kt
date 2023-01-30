package com.geektech.lesson1kyrs4.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.media.Image
import android.net.Uri
import androidx.core.graphics.drawable.toIcon

class Pref(private val context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    private val pref1 = context.getSharedPreferences(PREF1_NAME, MODE_PRIVATE)
    private val pref2 = context.getSharedPreferences(PREF2_NAME, MODE_PRIVATE)

    fun isUserSeen(): Boolean {
        return pref.getBoolean(SEEN_KEY, false)
    }

    fun saveSeen() {
        pref.edit().putBoolean(SEEN_KEY, true).apply()
    }

    fun saveName(name: String) {
        pref.edit().putString(NAME_KEY, name).apply()
    }

    fun getName(): String {
        return pref.getString(NAME_KEY, "").toString()
    }

    fun saveAge(age: String) {
        pref1.edit().putString(AGE_KEY, age.toString()).apply()
    }

    fun getAge(): String {
        return pref1.getString(AGE_KEY, "").toString()
    }

    fun saveImage(picture: String) {
        pref2.edit().putString(IMAGE_KEY, picture.toString()).apply()
    }

    fun getImage(): String {
        return pref2.getString(IMAGE_KEY, "").toString()
    }

    companion object {
        const val PREF_NAME = "Task.pref"
        const val PREF1_NAME = "Task1.pref"
        const val PREF2_NAME = "Task2.pref"
        const val SEEN_KEY = "seen.key"
        const val NAME_KEY = "name.key"
        const val AGE_KEY = "age.key"
        const val IMAGE_KEY = "image.key"
    }
}

