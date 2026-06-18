package com.smartnotes.selenium.pages

import com.smartnotes.selenium.utils.WaitUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class SettingsPage(private val driver: WebDriver) {

    private val profileNameField = By.xpath("//input[@name='name' or @id='profile-name']")
    private val saveSettingsBtn  = By.xpath("//button[contains(text(),'Save Settings') or contains(text(),'Save')]")
    private val themeToggleBtn   = By.xpath("//button[contains(@class,'theme') or contains(@aria-label,'theme') or contains(text(),'Theme')]")
    private val successBanner    = By.xpath("//*[contains(text(),'successfully') or contains(text(),'Saved')]")

    fun enterProfileName(name: String) {
        val el = WaitUtils.waitForVisible(driver, profileNameField)
        el.clear()
        el.sendKeys(name)
    }

    fun clickSave() {
        WaitUtils.waitForClickable(driver, saveSettingsBtn).click()
    }

    fun clickThemeToggle() {
        WaitUtils.waitForClickable(driver, themeToggleBtn).click()
    }

    fun isProfileNameVisible() = WaitUtils.isPresent(driver, profileNameField)
    fun isSaveButtonVisible() = WaitUtils.isPresent(driver, saveSettingsBtn)
    fun isThemeToggleVisible() = WaitUtils.isPresent(driver, themeToggleBtn)
    fun isSuccessBannerVisible() = WaitUtils.isPresent(driver, successBanner)
}
