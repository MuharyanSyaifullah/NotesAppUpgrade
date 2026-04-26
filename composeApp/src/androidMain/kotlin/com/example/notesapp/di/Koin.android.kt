package com.example.notesapp.di

import com.example.notesapp.platform.AndroidNetworkMonitor
import com.example.notesapp.platform.DeviceInfo
import com.example.notesapp.platform.AndroidDeviceInfo
import com.example.notesapp.platform.NetworkMonitor
import com.example.notesapp.db.NotesDatabase
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import com.example.notesapp.data.local.DatabaseProvider
import org.koin.core.module.Module
import org.koin.dsl.module

private val Context.dataStore by preferencesDataStore(name = "notes_settings")

actual val platformModule: Module = module {
    single<DeviceInfo> { AndroidDeviceInfo() }
    single<NetworkMonitor> { AndroidNetworkMonitor(get()) }
    single {
        val driver = AndroidSqliteDriver(NotesDatabase.Schema, get(), "notes.db")
        DatabaseProvider.getDatabase(driver)
    }
    single { get<Context>().dataStore }
}
