package com.example.cw.data.handlers

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.cw.domain.services.IUserService

class LocalizationHandler(private val userService: IUserService) {
    suspend fun changeLocale(context: Context, localeString: String, isOnlineUpdate: Boolean) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(localeString)
        } else {
            AppCompatDelegate
                .setApplicationLocales(LocaleListCompat.forLanguageTags(localeString))
        }
        userService.updateUserSettings(theme = null, locale = localeString, isOnlineUpdate)
        saveLanguagePreference(context, localeString)
    }

    private fun saveLanguagePreference(context: Context, languageCode: String) {
        val sharedPreferences =
            context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("language", languageCode)
        editor.apply()
    }

    private fun getSavedLanguagePreference(context: Context): String? {
        val sharedPreferences =
            context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString("language", "en")
    }

    suspend fun setLocale(context: Context, isOnlineUpdate: Boolean) {
//        val locale = getSavedLanguagePreference(context)
//        changeLocale(context, locale ?: "en", isOnlineUpdate)
    }
}