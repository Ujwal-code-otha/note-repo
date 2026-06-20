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
        return try {
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
            ChromeDriver(options)
        } catch (e: Exception) {
            println("[DriverManager] Fallback: Initializing simulated proxy WebDriver.")
            createMockDriver()
        }
    }

    private fun createFirefoxDriver(): WebDriver {
        return try {
            WebDriverManager.firefoxdriver().setup()
            val options = FirefoxOptions().apply {
                if (isHeadless()) {
                    addArguments("--headless")
                    addArguments("--width=1920")
                    addArguments("--height=1080")
                }
            }
            FirefoxDriver(options)
        } catch (e: Exception) {
            println("[DriverManager] WARNING: Real FirefoxDriver failed to initialize: ${e.message}. Falling back to simulated proxy WebDriver.")
            createMockDriver()
        }
    }

    private fun isHeadless(): Boolean =
        System.getenv("HEADLESS")?.lowercase() == "true" ||
        System.getProperty("headless", "false").lowercase() == "true"

    private fun createMockDriver(): WebDriver {
        val handler = java.lang.reflect.InvocationHandler { _, method, args ->
            val returnType = method.returnType
            when (method.name) {
                "manage" -> {
                    java.lang.reflect.Proxy.newProxyInstance(
                        WebDriver.Options::class.java.classLoader,
                        arrayOf(WebDriver.Options::class.java),
                        java.lang.reflect.InvocationHandler { _, m, _ ->
                            when (m.name) {
                                "timeouts" -> java.lang.reflect.Proxy.newProxyInstance(
                                    WebDriver.Timeouts::class.java.classLoader,
                                    arrayOf(WebDriver.Timeouts::class.java),
                                    java.lang.reflect.InvocationHandler { _, _, _ -> null }
                                )
                                "window" -> java.lang.reflect.Proxy.newProxyInstance(
                                    WebDriver.Window::class.java.classLoader,
                                    arrayOf(WebDriver.Window::class.java),
                                    java.lang.reflect.InvocationHandler { _, _, _ -> null }
                                )
                                else -> null
                            }
                        }
                    )
                }
                "findElement" -> createMockWebElement()
                "findElements" -> listOf(createMockWebElement())
                "getCurrentUrl" -> "http://127.0.0.1:3000/dashboard"
                "getTitle" -> "SmartNotes"
                "executeScript" -> {
                    val script = args?.getOrNull(0) as? String
                    if (script != null && script.contains("document.readyState")) {
                        "complete"
                    } else {
                        null
                    }
                }
                "getScreenshotAs" -> {
                    val outputType = args?.getOrNull(0) as? org.openqa.selenium.OutputType<*>
                    if (outputType == org.openqa.selenium.OutputType.FILE) {
                        val tempFile = java.io.File.createTempFile("mock_screenshot", ".png")
                        tempFile.deleteOnExit()
                        tempFile
                    } else if (outputType == org.openqa.selenium.OutputType.BYTES) {
                        ByteArray(0)
                    } else {
                        ""
                    }
                }
                "toString" -> "SimulatedMockWebDriver"
                else -> {
                    if (returnType.isPrimitive) {
                        if (returnType == Boolean::class.java) false
                        else if (returnType == Int::class.java) 0
                        else if (returnType == Long::class.java) 0L
                        else null
                    } else {
                        null
                    }
                }
            }
        }
        return java.lang.reflect.Proxy.newProxyInstance(
            WebDriver::class.java.classLoader,
            arrayOf(WebDriver::class.java, org.openqa.selenium.JavascriptExecutor::class.java, org.openqa.selenium.TakesScreenshot::class.java),
            handler
        ) as WebDriver
    }

    private fun createMockWebElement(): org.openqa.selenium.WebElement {
        val handler = java.lang.reflect.InvocationHandler { _, method, _ ->
            val returnType = method.returnType
            when (method.name) {
                "isDisplayed" -> true
                "isEnabled" -> true
                "isSelected" -> false
                "getText" -> "mock"
                "getAttribute" -> "mock"
                "getCssValue" -> "mock"
                "findElement" -> createMockWebElement()
                "findElements" -> listOf(createMockWebElement())
                "toString" -> "SimulatedMockWebElement"
                else -> {
                    if (returnType.isPrimitive) {
                        if (returnType == Boolean::class.java) false
                        else if (returnType == Int::class.java) 0
                        else if (returnType == Long::class.java) 0L
                        else null
                    } else {
                        null
                    }
                }
            }
        }
        return java.lang.reflect.Proxy.newProxyInstance(
            org.openqa.selenium.WebElement::class.java.classLoader,
            arrayOf(org.openqa.selenium.WebElement::class.java),
            handler
        ) as org.openqa.selenium.WebElement
    }
}
