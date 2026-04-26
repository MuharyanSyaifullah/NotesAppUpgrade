package com.example.notesapp.platform

interface DeviceInfo {
    val model: String
    val osVersion: String
    val platform: String
}

expect fun getDeviceInfo(): DeviceInfo
