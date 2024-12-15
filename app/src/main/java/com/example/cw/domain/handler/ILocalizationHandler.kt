package com.example.cw.domain.handler

interface ILocalizationHandler {
    fun getSavedLanguage(): String

    fun saveLanguage(language: String)

    fun setLocale(language: String)

    fun changeLanguage(language: String)
}