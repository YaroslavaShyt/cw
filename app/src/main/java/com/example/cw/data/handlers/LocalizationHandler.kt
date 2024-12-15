package com.example.cw.data.handlers

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.cw.domain.handler.ILocalizationHandler
import java.util.Locale

class LocalizationHandler(private val context: Context): ILocalizationHandler{
    private val preferences: SharedPreferences =
        context.getSharedPreferences("language_pref", Context.MODE_PRIVATE)

    companion object {
        const val LANGUAGE_KEY = "language_key"
        const val DEFAULT_LANGUAGE = "en"
    }

    override fun getSavedLanguage(): String {
        return preferences.getString(LANGUAGE_KEY, DEFAULT_LANGUAGE) ?: DEFAULT_LANGUAGE
    }

    override fun saveLanguage(language: String) {
        preferences.edit().putString(LANGUAGE_KEY, language).apply()
    }

    override fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)

        context.createConfigurationContext(config)

        saveLanguage(language)

        if (context is AppCompatActivity) {
            context.recreate()
        }
    }

    override fun changeLanguage(language: String) {
        setLocale(language)
    }
}