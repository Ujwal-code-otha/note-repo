package com.smartnotes.appium.utils

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

object PermissionHandler {
    fun dismissPermissionDialog(driver: AndroidDriver) {
        val allowButton = By.xpath("//android.widget.Button[@text='Allow' or @text='ALLOW']")
        try {
            if (WaitUtils.isPresent(driver, allowButton)) {
                driver.findElement(allowButton).click()
            }
        } catch (e: Exception) {
            // Ignore if dialog is not present
        }
    }
}
