package com.smartnotes.selenium.pages

import com.smartnotes.selenium.utils.WaitUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class LoginPage(private val driver: WebDriver) {

    // Locators
    private val emailInput    = By.xpath("//input[@type='email']")
    private val passwordInput = By.xpath("//input[@type='password']")
    private val signInButton  = By.xpath("//button[contains(text(),'Sign In') or contains(text(),'AUTHORIZE')]")
    private val googleButton  = By.xpath("//button[contains(text(),'Continue with Google') or contains(text(),'Google')]")
    private val forgotPassLink = By.xpath("//a[contains(text(),'Forgot') or contains(text(),'forgot')]")
    private val signUpLink    = By.xpath("//a[contains(text(),'Sign Up') or contains(text(),'Register') or contains(text(),'Create')]")
    private val errorMessage  = By.xpath("//*[contains(@class,'error') or contains(@class,'alert')]")
    private val pageHeading   = By.xpath("//*[contains(text(),'Sign In') or contains(text(),'Login') or contains(text(),'Welcome')]")

    fun navigate(baseUrl: String) {
        driver.get("$baseUrl/login")
        WaitUtils.waitForVisible(driver, emailInput)
    }

    fun enterEmail(email: String) {
        val el = WaitUtils.waitForVisible(driver, emailInput)
        el.clear()
        el.sendKeys(email)
    }

    fun enterPassword(password: String) {
        val el = WaitUtils.waitForVisible(driver, passwordInput)
        el.clear()
        el.sendKeys(password)
    }

    fun clickSignIn() {
        WaitUtils.waitForClickable(driver, signInButton).click()
    }

    fun login(email: String, password: String) {
        enterEmail(email)
        enterPassword(password)
        clickSignIn()
    }

    fun clickGoogleSignIn() = WaitUtils.waitForClickable(driver, googleButton).click()
    fun clickForgotPassword() = WaitUtils.waitForClickable(driver, forgotPassLink).click()
    fun clickSignUp() = WaitUtils.waitForClickable(driver, signUpLink).click()

    fun isEmailVisible()    = WaitUtils.isPresent(driver, emailInput)
    fun isPasswordVisible() = WaitUtils.isPresent(driver, passwordInput)
    fun isSignInEnabled()   = driver.findElements(signInButton).firstOrNull()?.isEnabled ?: false
    fun isGoogleVisible()   = WaitUtils.isPresent(driver, googleButton)
    fun isForgotPassVisible() = WaitUtils.isPresent(driver, forgotPassLink)
    fun isSignUpVisible()   = WaitUtils.isPresent(driver, signUpLink)
    fun isErrorVisible()    = WaitUtils.isPresent(driver, errorMessage)
    fun isHeadingVisible()  = WaitUtils.isPresent(driver, pageHeading)
    fun getPageTitle()      = driver.title
    fun getCurrentUrl()     = driver.currentUrl
}
