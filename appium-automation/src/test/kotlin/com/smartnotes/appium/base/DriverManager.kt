package com.smartnotes.appium.base

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import java.net.URL
import java.time.Duration

object DriverManager {

    fun createDriver(): AndroidDriver {
        val appPackage = System.getenv("APPIUM_APP_PACKAGE") ?: "com.ai.smart.notes"
        val appActivity = System.getenv("APPIUM_APP_ACTIVITY") ?: ".ui.MainActivity"
        val appiumServerUrl = System.getenv("APPIUM_SERVER_URL") ?: "http://127.0.0.1:4723"

        val deviceName = System.getenv("APPIUM_DEVICE_NAME") ?: run {
            try {
                val adbPath = "C:\\Users\\shrey\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb.exe"
                val process = java.lang.Runtime.getRuntime().exec(arrayOf(adbPath, "devices"))
                val reader = process.inputStream.bufferedReader()
                var line: String?
                var detectedName = "emulator-5554"
                while (reader.readLine().also { line = it } != null) {
                    val trimmed = line!!.trim()
                    val parts = trimmed.split(Regex("\\s+"))
                    if (parts.size == 2 && parts[1] == "device") {
                        detectedName = parts[0]
                        break
                    }
                }
                detectedName
            } catch (e: Exception) {
                "emulator-5554"
            }
        }

        val options = UiAutomator2Options().apply {
            setPlatformName("Android")
            setAutomationName("UiAutomator2")
            setDeviceName(deviceName)
            setAppPackage(appPackage)
            setAppActivity(appActivity)
            setNoReset(false)
            setNewCommandTimeout(Duration.ofSeconds(120))
        }

        return AndroidDriver(URL(appiumServerUrl), options)
    }
}
