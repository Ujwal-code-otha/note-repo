package com.smartnotes.appium.utils

import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object ScreenshotUtils {
    fun captureFailure(driver: WebDriver, testName: String) {
        try {
            val ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
            val cleanName = testName.replace("[^a-zA-Z0-9]".toRegex(), "_")
            val dest = File("build/screenshots/FAIL_${cleanName}_$ts.png")
            dest.parentFile.mkdirs()

            val src = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
            src.copyTo(dest, overwrite = true)
            println("[SCREENSHOT] Saved failure screen to: ${dest.absolutePath}")
        } catch (e: Exception) {
            println("[SCREENSHOT] Failed to capture: ${e.message}")
        }
    }
}
