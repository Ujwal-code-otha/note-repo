package com.smartnotes.appium.base

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import java.net.URL
import java.time.Duration

object DriverManager {

    fun createDriver(): AndroidDriver {
        val appPackage        = System.getenv("APPIUM_APP_PACKAGE")  ?: "com.ai.smart.notes"
        val appActivity       = System.getenv("APPIUM_APP_ACTIVITY") ?: ".ui.MainActivity"
        val appiumServerUrl   = System.getenv("APPIUM_SERVER_URL")   ?: "http://127.0.0.1:4723"
        val envUdid           = System.getenv("APPIUM_UDID")
        val envDeviceName     = System.getenv("APPIUM_DEVICE_NAME")
        val envPlatformVer    = System.getenv("APPIUM_PLATFORM_VERSION") ?: "11"

        // Detect connected device via ADB (supports Windows and Linux/CI)
        val (detectedUdid, detectedName) = detectDevice()

        val udid       = envUdid       ?: detectedUdid ?: "emulator-5554"
        val deviceName = envDeviceName ?: detectedName ?: "Android Emulator"

        val options = UiAutomator2Options().apply {
            setPlatformName("Android")
            setAutomationName("UiAutomator2")
            setDeviceName(deviceName)
            setUdid(udid)
            setPlatformVersion(envPlatformVer)
            setAppPackage(appPackage)
            setAppActivity(appActivity)
            setNoReset(true)           // Keep app state between tests (same session)
            setAutoGrantPermissions(true)
            setNewCommandTimeout(Duration.ofSeconds(120))
            setAdbExecTimeout(Duration.ofSeconds(60))
            setCapability("appium:disableWindowAnimation", true)
            setCapability("appium:skipUnlock", true)
            setCapability("appium:allowTestPackages", true)
            setCapability("appium:ignoreHiddenApiPolicyError", true)
        }

        println("[DriverManager] Connecting to Appium: $appiumServerUrl")
        println("[DriverManager] Device: $deviceName ($udid)")
        return AndroidDriver(URL(appiumServerUrl), options)
    }

    /**
     * Detect first connected ADB device — works on Windows (local) and Linux (CI).
     */
    private fun detectDevice(): Pair<String?, String?> {
        // Try system adb first (Linux/macOS/CI PATH), then Windows local path
        val adbCandidates = listOf(
            "adb",
            "C:\\Users\\shrey\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb.exe"
        )
        for (adb in adbCandidates) {
            try {
                val cmd = if (adb.endsWith(".exe")) arrayOf(adb, "devices") else arrayOf("adb", "devices")
                val process = Runtime.getRuntime().exec(cmd)
                val reader  = process.inputStream.bufferedReader()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    val parts = line!!.trim().split(Regex("\\s+"))
                    if (parts.size == 2 && parts[1] == "device") {
                        val udid = parts[0]
                        val name = if (udid.startsWith("emulator")) "Android Emulator" else "Android Physical Device"
                        println("[DriverManager] Detected device: $udid")
                        return Pair(udid, name)
                    }
                }
                break // adb ran but no device — don't try next candidate
            } catch (_: Exception) {}
        }
        return Pair(null, null)
    }
}
