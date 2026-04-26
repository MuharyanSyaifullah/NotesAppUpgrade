package com.example.notesapp.di

import com.example.notesapp.data.repository.NotesRepository
import com.example.notesapp.data.repository.SettingsRepository
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.SettingsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {
    singleOf(::NotesRepository)
    singleOf(::SettingsRepository)
    factoryOf(::NotesViewModel)
    factoryOf(::SettingsViewModel)
}

expect val platformModule: Module
