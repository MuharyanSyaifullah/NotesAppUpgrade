package com.example.notesapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.notesapp.data.settings.SettingsKeys
import com.example.notesapp.model.SortOrder
import com.example.notesapp.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(private val dataStore: DataStore<Preferences>) {

    private val themeKey = stringPreferencesKey(SettingsKeys.THEME_MODE)
    private val sortKey = stringPreferencesKey(SettingsKeys.SORT_ORDER)

    val themeModeFlow: Flow<ThemeMode> = dataStore.data.map { prefs ->
        ThemeMode.valueOf(prefs[themeKey] ?: ThemeMode.SYSTEM.name)
    }

    val sortOrderFlow: Flow<SortOrder> = dataStore.data.map { prefs ->
        SortOrder.valueOf(prefs[sortKey] ?: SortOrder.NEWEST.name)
    }

    suspend fun setThemeMode(themeMode: ThemeMode) {
        dataStore.edit { prefs ->
            prefs[themeKey] = themeMode.name
        }
    }

    suspend fun setSortOrder(sortOrder: SortOrder) {
        dataStore.edit { prefs ->
            prefs[sortKey] = sortOrder.name
        }
    }
}