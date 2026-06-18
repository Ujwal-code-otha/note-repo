package com.smartnotes.selenium.pages

import com.smartnotes.selenium.utils.WaitUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class DashboardPage(private val driver: WebDriver) {

    private val newNoteButton  = By.xpath("//button[contains(text(),'New Note') or contains(@aria-label,'New Note') or contains(text(),'+')]")
    private val searchInput    = By.xpath("//input[contains(@placeholder,'Search') or contains(@placeholder,'search')]")
    private val sidebarNav     = By.xpath("//*[contains(@class,'sidebar') or contains(@class,'nav')]")
    private val notesList      = By.xpath("//*[contains(@class,'note') or contains(@class,'list')]")
    private val userProfile    = By.xpath("//*[contains(@class,'profile') or contains(@class,'user') or contains(@class,'avatar')]")
    private val settingsButton = By.xpath("//button[contains(@aria-label,'Settings') or contains(@title,'Settings')]")
    private val allTab         = By.xpath("//button[contains(text(),'All') or contains(text(),'all')]")
    private val favesTab       = By.xpath("//button[contains(text(),'Fave') or contains(text(),'Favorite') or contains(text(),'Star')]")
    private val logoutButton   = By.xpath("//button[contains(text(),'Logout') or contains(text(),'Sign Out') or contains(text(),'Log out')]")
    private val editorArea     = By.xpath("//*[contains(@class,'editor') or contains(@class,'workspace') or contains(@contenteditable,'true')]")

    fun waitForLoad() {
        WaitUtils.waitForUrl(driver, "/dashboard")
    }

    fun clickNewNote()     = WaitUtils.waitForClickable(driver, newNoteButton).click()
    fun clickSettings()    { driver.findElements(settingsButton).firstOrNull()?.click() }
    fun clickLogout()      { driver.findElements(logoutButton).firstOrNull()?.click() }
    fun clickAllTab()      { driver.findElements(allTab).firstOrNull()?.click() }
    fun clickFavesTab()    { driver.findElements(favesTab).firstOrNull()?.click() }

    fun search(term: String) {
        val el = driver.findElements(searchInput).firstOrNull() ?: return
        el.clear(); el.sendKeys(term)
        Thread.sleep(800)
    }

    fun isNewNoteVisible()   = WaitUtils.isPresent(driver, newNoteButton)
    fun isSearchVisible()    = WaitUtils.isPresent(driver, searchInput)
    fun isSidebarVisible()   = WaitUtils.isPresent(driver, sidebarNav)
    fun isNotesListVisible() = WaitUtils.isPresent(driver, notesList)
    fun isUserProfileVisible()= WaitUtils.isPresent(driver, userProfile)
    fun isAllTabVisible()    = WaitUtils.isPresent(driver, allTab)
    fun isFavesTabVisible()  = WaitUtils.isPresent(driver, favesTab)
    fun isEditorVisible()    = WaitUtils.isPresent(driver, editorArea)
    fun getCurrentUrl()      = driver.currentUrl
    fun getPageTitle()       = driver.title
    fun getPageSource()      = driver.pageSource
}
