package com.smartnotes.selenium.pages

import com.smartnotes.selenium.utils.WaitUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class NavigationPage(private val driver: WebDriver) {

    private val homeLink      = By.xpath("//a[contains(@href,'/dashboard') or contains(text(),'Home')]")
    private val settingsLink  = By.xpath("//a[contains(@href,'/settings') or contains(text(),'Settings')]")
    private val profileLink   = By.xpath("//a[contains(@href,'/profile') or contains(text(),'Profile')]")
    private val notesLink     = By.xpath("//a[contains(@href,'/notes') or contains(text(),'Notes')]")

    fun clickHome() = WaitUtils.waitForClickable(driver, homeLink).click()
    fun clickSettings() = WaitUtils.waitForClickable(driver, settingsLink).click()
    fun clickProfile() = WaitUtils.waitForClickable(driver, profileLink).click()
    fun clickNotes() = WaitUtils.waitForClickable(driver, notesLink).click()

    fun isHomeLinkVisible() = WaitUtils.isPresent(driver, homeLink)
    fun isSettingsLinkVisible() = WaitUtils.isPresent(driver, settingsLink)
    fun isProfileLinkVisible() = WaitUtils.isPresent(driver, profileLink)
    fun isNotesLinkVisible() = WaitUtils.isPresent(driver, notesLink)
}
