package com.example.notesapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp.model.SortOrder
import com.example.notesapp.model.ThemeMode
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    notesViewModel: NotesViewModel,
    settingsViewModel: SettingsViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    val themeMode by notesViewModel.themeMode.collectAsState()
    val sortOrder by notesViewModel.sortOrder.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Theme", style = MaterialTheme.typography.titleMedium)
            ThemeMode.entries.forEach { mode ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = themeMode == mode,
                        onClick = { notesViewModel.setThemeMode(mode) }
                    )
                    Text(mode.name, modifier = Modifier.padding(start = 8.dp))
                }
            }

            HorizontalDivider()

            Text("Sort Order", style = MaterialTheme.typography.titleMedium)
            SortOrder.entries.forEach { order ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = sortOrder == order,
                        onClick = { notesViewModel.setSortOrder(order) }
                    )
                    Text(order.name, modifier = Modifier.padding(start = 8.dp))
                }
            }

            HorizontalDivider()

            Text("Platform Info", style = MaterialTheme.typography.titleMedium)
            Text("Platform: ${settingsViewModel.platform}")
            Text("Model: ${settingsViewModel.deviceModel}")
            Text("OS: ${settingsViewModel.osVersion}")
        }
    }
}
