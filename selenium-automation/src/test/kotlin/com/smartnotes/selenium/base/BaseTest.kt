package com.smartnotes.selenium.base

import com.smartnotes.selenium.utils.ScreenshotUtils
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

@ExtendWith(RetryExtension::class, TestResultWatcher::class)
abstract class BaseTest {

    protected val driver: WebDriver
        get() = sharedDriver ?: throw IllegalStateException("Driver not initialized")

    protected lateinit var wait: WebDriverWait

    companion object {
        const val BASE_URL = "http://127.0.0.1:3001"
        const val DEFAULT_TIMEOUT = 10L
        const val SHORT_TIMEOUT = 3L

        private var sharedDriver: WebDriver? = null

        @BeforeAll
        @JvmStatic
        fun beforeAllClass() {
            sharedDriver = DriverManager.createDriver()
            sharedDriver?.manage()?.window()?.maximize()
            sharedDriver?.manage()?.timeouts()?.implicitlyWait(Duration.ofSeconds(3))
        }

        @AfterAll
        @JvmStatic
        fun afterAllClass() {
            sharedDriver?.quit()
            sharedDriver = null
        }
    }

    @BeforeEach
    fun setUp(testInfo: TestInfo) {
        wait = WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
        println("[SETUP] Starting: ${testInfo.displayName}")
    }

    @AfterEach
    fun tearDown(testInfo: TestInfo) {
        val passed = testInfo.tags.contains("passed")
        if (!passed) {
            try {
                ScreenshotUtils.captureFailure(driver, testInfo.displayName)
            } catch (e: Exception) {}
        }
        println("[TEARDOWN] Completed: ${testInfo.displayName}")
    }
}
