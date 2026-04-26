package com.example.notesapp.platform

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

class AndroidNetworkMonitor(
    private val context: Context
) : NetworkMonitor {
    override val isOnline: Flow<Boolean> = callbackFlow {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                channel.trySend(true)
            }

            override fun onLost(network: Network) {
                channel.trySend(false)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        
        connectivityManager.registerNetworkCallback(request, callback)

        channel.trySend(connectivityManager.activeNetwork != null)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.conflate()
}

// Note: This needs a context, so it will be provided via Koin actual instead of a simple actual fun if possible,
// but for 'expect fun' we might need a different approach or just use Koin to inject it.
// To keep it simple for now as requested:
actual fun getNetworkMonitor(): NetworkMonitor {
    // This is tricky because actual fun doesn't have access to context.
    // In a real app, we'd use Koin to provide the implementation.
    throw IllegalStateException("Use Koin to inject NetworkMonitor")
}
