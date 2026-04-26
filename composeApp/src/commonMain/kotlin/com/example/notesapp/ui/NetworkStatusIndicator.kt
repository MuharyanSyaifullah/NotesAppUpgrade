package com.example.notesapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notesapp.platform.NetworkMonitor
import org.koin.compose.koinInject

@Composable
fun NetworkStatusIndicator(
    networkMonitor: NetworkMonitor = koinInject()
) {
    val isOnline by networkMonitor.isOnline.collectAsState(initial = true)

    AnimatedVisibility(visible = !isOnline) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.errorContainer
        ) {
            Row(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "No Internet Connection",
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
