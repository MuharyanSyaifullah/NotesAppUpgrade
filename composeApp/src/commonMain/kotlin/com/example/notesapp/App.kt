package com.example.notesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.notesapp.model.ThemeMode
import com.example.notesapp.ui.MainScreen
import com.example.notesapp.ui.NetworkStatusIndicator
import com.example.notesapp.ui.SettingScreen
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.viewmodel.NotesViewModel
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

enum class Screen {
    Main, Settings
}

@Composable
fun App() {
    KoinContext {
        val viewModel: NotesViewModel = koinViewModel()
        val themeMode by viewModel.themeMode.collectAsState()

        val darkTheme = when (themeMode) {
            ThemeMode.LIGHT -> false
            ThemeMode.DARK -> true
            ThemeMode.SYSTEM -> false
        }

        var currentScreen by remember { mutableStateOf(Screen.Main) }

        NotesAppTheme(darkTheme = darkTheme) {
            Column(modifier = Modifier.fillMaxSize()) {
                NetworkStatusIndicator()
                when (currentScreen) {
                    Screen.Main -> MainScreen(
                        viewModel = viewModel,
                        onNavigateToSettings = { currentScreen = Screen.Settings }
                    )
                    Screen.Settings -> SettingScreen(
                        notesViewModel = viewModel,
                        onNavigateBack = { currentScreen = Screen.Main }
                    )
                }
            }
        }
    }
}
