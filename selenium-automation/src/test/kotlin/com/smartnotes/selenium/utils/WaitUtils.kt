package com.smartnotes.selenium.utils

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

object WaitUtils {

    fun waitForVisible(driver: WebDriver, by: By, timeoutSec: Long = 20): WebElement {
        return WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
            .until(ExpectedConditions.visibilityOfElementLocated(by))
    }

    fun waitForClickable(driver: WebDriver, by: By, timeoutSec: Long = 20): WebElement {
        return WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
            .until(ExpectedConditions.elementToBeClickable(by))
    }

    fun waitForUrl(driver: WebDriver, urlFragment: String, timeoutSec: Long = 30) {
        WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
            .until(ExpectedConditions.urlContains(urlFragment))
    }

    fun waitForText(driver: WebDriver, by: By, text: String, timeoutSec: Long = 20): WebElement {
        return WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
            .until(ExpectedConditions.textToBePresentInElementLocated(by, text))
            .let { driver.findElement(by) }
    }

    fun waitForPageLoad(driver: WebDriver, timeoutSec: Long = 30) {
        WebDriverWait(driver, Duration.ofSeconds(timeoutSec)).until { d ->
            (d as JavascriptExecutor).executeScript("return document.readyState") == "complete"
        }
    }

    fun waitForAbsence(driver: WebDriver, by: By, timeoutSec: Long = 10) {
        WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
            .until(ExpectedConditions.invisibilityOfElementLocated(by))
    }

    fun isPresent(driver: WebDriver, by: By): Boolean {
        return try {
            driver.findElements(by).isNotEmpty()
        } catch (e: Exception) { false }
    }
}
