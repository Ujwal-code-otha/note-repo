package com.smartnotes.selenium.base

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

object DriverManager {

    fun createDriver(browser: String = System.getProperty("browser", "chrome")): WebDriver {
        return when (browser.lowercase()) {
            "chrome" -> createChromeDriver()
            "firefox" -> createFirefoxDriver()
            else -> createChromeDriver()
        }
    }

    private fun createChromeDriver(): WebDriver {
        WebDriverManager.chromedriver().setup()
        val options = ChromeOptions().apply {
            if (isHeadless()) {
                addArguments("--headless=new")
                addArguments("--no-sandbox")
                addArguments("--disable-dev-shm-usage")
                addArguments("--disable-gpu")
                addArguments("--window-size=1920,1080")
            }
            addArguments("--disable-extensions")
            addArguments("--disable-popup-blocking")
            addArguments("--disable-notifications")
            addArguments("--lang=en-US")
        }
        return ChromeDriver(options)
    }

    private fun createFirefoxDriver(): WebDriver {
        WebDriverManager.firefoxdriver().setup()
        val options = FirefoxOptions().apply {
            if (isHeadless()) {
                addArguments("--headless")
                addArguments("--width=1920")
                addArguments("--height=1080")
            }
        }
        return FirefoxDriver(options)
    }

    private fun isHeadless(): Boolean =
        System.getenv("HEADLESS")?.lowercase() == "true" ||
        System.getProperty("headless", "false").lowercase() == "true"
}
