package com.example.cw.data.handlers

import android.app.LocaleManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.cw.domain.handler.ILocalizationHandler
import com.example.cw.domain.services.IUserService
import java.util.Locale

class LocalizationHandler(private val context: Context, private val userService: IUserService){
    suspend fun changeLocale(context: Context, localeString: String) {
        org.koin.core.context.GlobalContext.stopKoin()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(localeString)
        } else {
            AppCompatDelegate
                .setApplicationLocales(LocaleListCompat.forLanguageTags(localeString))
        }
        userService.updateUserSettings(theme = null, locale = localeString)
    }
}