package com.smartnotes.selenium.base

import com.smartnotes.selenium.utils.ExcelReporter
import com.smartnotes.selenium.utils.ScreenshotUtils
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

@ExtendWith(RetryExtension::class, TestResultWatcher::class)
abstract class BaseTest {

    protected lateinit var driver: WebDriver
    protected lateinit var wait: WebDriverWait

    companion object {
        const val BASE_URL = "http://localhost:3000"
        const val DEFAULT_TIMEOUT = 20L
        const val SHORT_TIMEOUT = 5L
    }

    @BeforeEach
    fun setUp(testInfo: TestInfo) {
        driver = DriverManager.createDriver()
        driver.manage().window().maximize()
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5))
        wait = WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
        println("[SETUP] Starting: ${testInfo.displayName}")
    }

    @AfterEach
    fun tearDown(testInfo: TestInfo) {
        val passed = testInfo.tags.contains("passed")
        try {
            if (!passed) {
                ScreenshotUtils.captureFailure(driver, testInfo.displayName)
            }
        } finally {
            driver.quit()
            println("[TEARDOWN] Completed: ${testInfo.displayName}")
        }
    }
}
