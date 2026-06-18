package com.smartnotes.appium.pages

import com.smartnotes.appium.utils.WaitUtils
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class LoginPage(private val driver: AndroidDriver) {
    private val emailInput = By.xpath("//android.widget.EditText[contains(@resource-id,'email') or contains(@text,'Email')]")
    private val passwordInput = By.xpath("//android.widget.EditText[contains(@resource-id,'password') or contains(@text,'Password')]")
    private val signInBtn = By.xpath("//android.widget.Button[contains(@text,'Sign In') or contains(@text,'Login')]")

    fun login(email: String, pass: String) {
        try {
            WaitUtils.waitForVisible(driver, emailInput).apply { clear(); sendKeys(email) }
            driver.findElement(passwordInput).apply { clear(); sendKeys(pass) }
            driver.findElement(signInBtn).click()
        } catch (e: Exception) {}
    }
    fun isEmailVisible() = WaitUtils.isPresent(driver, emailInput)
}

class RegistrationPage(private val driver: AndroidDriver) {
    private val nameInput = By.xpath("//android.widget.EditText[contains(@text,'Name')]")
    private val emailInput = By.xpath("//android.widget.EditText[contains(@text,'Email')]")
    fun isNameVisible() = WaitUtils.isPresent(driver, nameInput)
}

class ForgotPasswordPage(private val driver: AndroidDriver) {
    private val emailInput = By.xpath("//android.widget.EditText[contains(@text,'Email')]")
    fun isEmailVisible() = WaitUtils.isPresent(driver, emailInput)
}

class DashboardPage(private val driver: AndroidDriver) {
    private val newNoteBtn = By.xpath("//android.widget.Button[contains(@text,'+') or contains(@content-desc,'Add')]")
    fun isAddNoteVisible() = WaitUtils.isPresent(driver, newNoteBtn)
}

class NotesPage(private val driver: AndroidDriver) {
    private val titleInput = By.xpath("//android.widget.EditText[contains(@text,'Title')]")
    fun isTitleVisible() = WaitUtils.isPresent(driver, titleInput)
}

class SearchPage(private val driver: AndroidDriver) {
    private val searchBar = By.xpath("//android.widget.EditText[contains(@text,'Search')]")
    fun isSearchBarVisible() = WaitUtils.isPresent(driver, searchBar)
}

class NavigationPage(private val driver: AndroidDriver) {
    private val navMenu = By.xpath("//android.widget.ImageButton[contains(@content-desc,'Navigate') or contains(@content-desc,'Open')]")
    fun isNavMenuVisible() = WaitUtils.isPresent(driver, navMenu)
}

class SettingsPage(private val driver: AndroidDriver) {
    private val settingsHeader = By.xpath("//android.widget.TextView[contains(@text,'Settings')]")
    fun isSettingsVisible() = WaitUtils.isPresent(driver, settingsHeader)
}

class LogoutPage(private val driver: AndroidDriver) {
    private val logoutBtn = By.xpath("//android.widget.Button[contains(@text,'Logout') or contains(@text,'Sign Out')]")
    fun isLogoutVisible() = WaitUtils.isPresent(driver, logoutBtn)
}
