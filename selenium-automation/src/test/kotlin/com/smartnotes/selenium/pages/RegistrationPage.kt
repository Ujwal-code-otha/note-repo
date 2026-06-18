package com.smartnotes.selenium.pages

import com.smartnotes.selenium.utils.WaitUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class RegistrationPage(private val driver: WebDriver) {

    private val nameInput       = By.xpath("//input[@name='name' or @placeholder='Name' or @placeholder='Full Name']")
    private val emailInput      = By.xpath("//input[@type='email']")
    private val passwordInput   = By.xpath("(//input[@type='password'])[1]")
    private val confirmPassInput= By.xpath("(//input[@type='password'])[2]")
    private val signUpButton    = By.xpath("//button[contains(text(),'Sign Up') or contains(text(),'Register') or contains(text(),'Create')]")
    private val loginLink       = By.xpath("//a[contains(text(),'Sign In') or contains(text(),'Login') or contains(text(),'Already')]")
    private val errorMessage    = By.xpath("//*[contains(@class,'error') or contains(@class,'alert')]")
    private val googleButton    = By.xpath("//button[contains(text(),'Google')]")

    fun navigate(baseUrl: String) {
        driver.get("$baseUrl/signup")
        WaitUtils.waitForVisible(driver, emailInput)
    }

    fun enterName(name: String) {
        val el = driver.findElements(nameInput).firstOrNull() ?: return
        el.clear(); el.sendKeys(name)
    }

    fun enterEmail(email: String) {
        val el = WaitUtils.waitForVisible(driver, emailInput)
        el.clear(); el.sendKeys(email)
    }

    fun enterPassword(password: String) {
        val el = WaitUtils.waitForVisible(driver, passwordInput)
        el.clear(); el.sendKeys(password)
    }

    fun enterConfirmPassword(password: String) {
        val el = driver.findElements(confirmPassInput).firstOrNull() ?: return
        el.clear(); el.sendKeys(password)
    }

    fun clickSignUp() = WaitUtils.waitForClickable(driver, signUpButton).click()
    fun clickLogin()  = WaitUtils.waitForClickable(driver, loginLink).click()

    fun isEmailVisible()   = WaitUtils.isPresent(driver, emailInput)
    fun isSignUpEnabled()  = driver.findElements(signUpButton).firstOrNull()?.isEnabled ?: false
    fun isLoginLinkVisible() = WaitUtils.isPresent(driver, loginLink)
    fun isGoogleVisible()  = WaitUtils.isPresent(driver, googleButton)
    fun isErrorVisible()   = WaitUtils.isPresent(driver, errorMessage)
    fun getPageTitle()     = driver.title
}
