package com.smartnotes.appium.base

import io.appium.java_client.android.AndroidDriver
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.extension.ExtendWith
import java.time.Duration

@ExtendWith(RetryExtension::class, TestResultWatcher::class)
abstract class BaseTest {

    protected lateinit var driver: AndroidDriver

    @BeforeEach
    fun setUp(testInfo: TestInfo) {
        try {
            driver = DriverManager.createDriver()
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
            println("[SETUP] Appium starting: ${testInfo.displayName}")
        } catch (e: Exception) {
            println("[SETUP] Appium failed to start driver. Logging dummy driver context.")
        }
    }

    @AfterEach
    fun tearDown(testInfo: TestInfo) {
        try {
            if (::driver.isInitialized) {
                driver.quit()
            }
        } finally {
            println("[TEARDOWN] Appium completed: ${testInfo.displayName}")
        }
    }
}
