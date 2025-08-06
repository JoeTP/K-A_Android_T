package com.example.khatibalamytask.data.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class PreferencesManager @Inject constructor(
    context: Context
) {

    companion object {
        private const val PREF_NAME = "app_prefs"
        private const val MODE = Context.MODE_PRIVATE
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, MODE)

    fun saveString(key: String, value: String) {
        sharedPreferences.edit { putString(key, value) }
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun clearKey(key: String) {
        sharedPreferences.edit { remove(key) }
    }
}
