package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notesapp.platform.DeviceInfo
import com.example.notesapp.data.repository.SettingsRepository

class SettingsViewModel(
    private val deviceInfo: DeviceInfo,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val deviceModel: String = deviceInfo.model
    val osVersion: String = deviceInfo.osVersion
    val platform: String = deviceInfo.platform
}
