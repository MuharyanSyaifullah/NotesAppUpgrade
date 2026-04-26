package com.example.notesapp.platform

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}

expect fun getNetworkMonitor(): NetworkMonitor
