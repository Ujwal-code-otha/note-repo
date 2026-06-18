package com.smartnotes.appium.utils

import io.appium.java_client.android.AndroidDriver

object KeyboardUtils {
    fun hideKeyboard(driver: AndroidDriver) {
        try {
            driver.hideKeyboard()
        } catch (e: Exception) {
            // Ignore if keyboard is already hidden
        }
    }
}
