package com.example.notesapp.di

import com.example.notesapp.data.repository.NotesRepository
import com.example.notesapp.data.repository.SettingsRepository
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.SettingsViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule, platformModule)
    }

// For iOS
fun initKoin() = initKoin {}

