package com.smartnotes.selenium.pages

import com.smartnotes.selenium.utils.WaitUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class ForgotPasswordPage(private val driver: WebDriver) {

    private val emailInput     = By.xpath("//input[@type='email']")
    private val submitButton   = By.xpath("//button[contains(text(),'Reset') or contains(text(),'Send') or contains(text(),'Submit')]")
    private val backToLogin    = By.xpath("//a[contains(text(),'Back') or contains(text(),'Sign In') or contains(text(),'Login')]")
    private val successMessage = By.xpath("//*[contains(text(),'sent') or contains(text(),'check') or contains(@class,'success')]")
    private val errorMessage   = By.xpath("//*[contains(@class,'error') or contains(@class,'alert')]")
    private val pageHeading    = By.xpath("//*[contains(text(),'Forgot') or contains(text(),'Reset') or contains(text(),'Password')]")

    fun navigate(baseUrl: String) {
        driver.get("$baseUrl/forgot-password")
        Thread.sleep(1000)
    }

    fun navigateViaLogin(baseUrl: String) {
        driver.get("$baseUrl/login")
        WaitUtils.waitForVisible(driver, By.xpath("//input[@type='email']"))
        val forgotLink = By.xpath("//a[contains(text(),'Forgot') or contains(text(),'forgot')]")
        if (WaitUtils.isPresent(driver, forgotLink)) {
            driver.findElement(forgotLink).click()
            Thread.sleep(1000)
        }
    }

    fun enterEmail(email: String) {
        val el = WaitUtils.waitForVisible(driver, emailInput)
        el.clear(); el.sendKeys(email)
    }

    fun clickSubmit() = WaitUtils.waitForClickable(driver, submitButton).click()
    fun clickBackToLogin() { driver.findElements(backToLogin).firstOrNull()?.click() }

    fun isEmailVisible()     = WaitUtils.isPresent(driver, emailInput)
    fun isSubmitEnabled()    = driver.findElements(submitButton).firstOrNull()?.isEnabled ?: false
    fun isBackLinkVisible()  = WaitUtils.isPresent(driver, backToLogin)
    fun isSuccessVisible()   = WaitUtils.isPresent(driver, successMessage)
    fun isErrorVisible()     = WaitUtils.isPresent(driver, errorMessage)
    fun isHeadingVisible()   = WaitUtils.isPresent(driver, pageHeading)
    fun getPageTitle()       = driver.title
    fun getCurrentUrl()      = driver.currentUrl
}
