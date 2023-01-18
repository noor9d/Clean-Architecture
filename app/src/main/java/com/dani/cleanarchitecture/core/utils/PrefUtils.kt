package com.dani.cleanarchitecture.core.utils

import android.preference.PreferenceManager
import androidx.core.content.edit
import com.dani.cleanarchitecture.App
import com.dani.cleanarchitecture.core.IS_FIRST_TIME

object PrefUtils {
    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getContext())


    var isFirstTime
        get() = sharedPreferences.getBoolean(
            IS_FIRST_TIME,
            false
        )
        set(value) = sharedPreferences.edit { putBoolean(IS_FIRST_TIME, value) }
}