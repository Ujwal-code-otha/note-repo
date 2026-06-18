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

        val options = UiAutomator2Options().apply {
            setPlatformName("Android")
            setAutomationName("UiAutomator2")
            setDeviceName(System.getenv("APPIUM_DEVICE_NAME") ?: "emulator-5554")
            setAppPackage(appPackage)
            setAppActivity(appActivity)
            setNoReset(false)
            setNewCommandTimeout(Duration.ofSeconds(120))
        }

        return AndroidDriver(URL(appiumServerUrl), options)
    }
}
