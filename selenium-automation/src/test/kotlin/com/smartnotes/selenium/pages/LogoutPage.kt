package com.smartnotes.selenium.pages

import com.smartnotes.selenium.utils.WaitUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class LogoutPage(private val driver: WebDriver) {

    private val logoutButton = By.xpath("//button[contains(text(),'Logout') or contains(text(),'Sign Out') or contains(text(),'Log out')]")
    private val confirmBtn   = By.xpath("//button[contains(text(),'Yes') or contains(text(),'Confirm')]")

    fun clickLogout() {
        WaitUtils.waitForClickable(driver, logoutButton).click()
    }

    fun clickConfirm() {
        if (WaitUtils.isPresent(driver, confirmBtn)) {
            driver.findElement(confirmBtn).click()
        }
    }

    fun isLogoutButtonVisible() = WaitUtils.isPresent(driver, logoutButton)
}
