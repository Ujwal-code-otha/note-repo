package com.smartnotes.appium.utils

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

object WaitUtils {

    fun waitForVisible(driver: WebDriver, locator: By, timeoutSeconds: Long = 10): WebElement {
        val wait = WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
    }

    fun waitForClickable(driver: WebDriver, locator: By, timeoutSeconds: Long = 10): WebElement {
        val wait = WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
        return wait.until(ExpectedConditions.elementToBeClickable(locator))
    }

    fun isPresent(driver: WebDriver, locator: By): Boolean {
        return try {
            driver.findElements(locator).isNotEmpty()
        } catch (e: Exception) {
            false
        }
    }
}
