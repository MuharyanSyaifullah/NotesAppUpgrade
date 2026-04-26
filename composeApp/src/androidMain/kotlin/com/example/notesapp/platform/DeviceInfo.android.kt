package com.example.notesapp.platform

import android.os.Build

class AndroidDeviceInfo : DeviceInfo {
    override val model: String = "${Build.MANUFACTURER} ${Build.MODEL}"
    override val osVersion: String = "Android ${Build.VERSION.RELEASE}"
    override val platform: String = "Android"
}

actual fun getDeviceInfo(): DeviceInfo = AndroidDeviceInfo()
