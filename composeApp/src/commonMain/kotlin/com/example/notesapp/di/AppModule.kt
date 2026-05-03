package com.example.notesapp.di

import com.example.notesapp.data.remote.ai.GeminiService
import com.example.notesapp.data.repository.AiRepository
import com.example.notesapp.data.repository.NotesRepository
import com.example.notesapp.data.repository.SettingsRepository
import com.example.notesapp.viewmodel.AiViewModel
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.SettingsViewModel
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Logging) {
                level = LogLevel.INFO
            }
        }
    }
    
    // AI Components
    single { GeminiService(get(), "YOUR_API_KEY_HERE") }
    singleOf(::AiRepository)
    
    singleOf(::NotesRepository)
    singleOf(::SettingsRepository)
    factoryOf(::NotesViewModel)
    factoryOf(::SettingsViewModel)
    factoryOf(::AiViewModel)
}

expect val platformModule: Module
