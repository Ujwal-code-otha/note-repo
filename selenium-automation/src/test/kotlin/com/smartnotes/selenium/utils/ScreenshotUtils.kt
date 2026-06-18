package com.smartnotes.selenium.utils

import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object ScreenshotUtils {

    private val screenshotDir = File("build/screenshots").also { it.mkdirs() }
    private val formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")

    fun captureFailure(driver: WebDriver, testName: String): String? {
        return try {
            val ts = LocalDateTime.now().format(formatter)
            val safeName = testName.replace(Regex("[^A-Za-z0-9_\\-]"), "_").take(80)
            val file = File(screenshotDir, "FAIL_${safeName}_$ts.png")
            val src = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
            src.copyTo(file, overwrite = true)
            println("[SCREENSHOT] Saved: ${file.absolutePath}")
            file.absolutePath
        } catch (e: Exception) {
            println("[SCREENSHOT] Failed to capture: ${e.message}")
            null
        }
    }

    fun capture(driver: WebDriver, label: String): String? {
        return try {
            val ts = LocalDateTime.now().format(formatter)
            val safeName = label.replace(Regex("[^A-Za-z0-9_\\-]"), "_").take(80)
            val file = File(screenshotDir, "${safeName}_$ts.png")
            val src = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
            src.copyTo(file, overwrite = true)
            file.absolutePath
        } catch (e: Exception) {
            null
        }
    }
}
