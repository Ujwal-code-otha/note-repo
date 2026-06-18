package com.smartnotes.selenium.pages

import com.smartnotes.selenium.utils.WaitUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class SearchPage(private val driver: WebDriver) {

    private val searchBarInput = By.xpath("//input[contains(@placeholder,'Search') or contains(@placeholder,'search')]")
    private val searchResults  = By.xpath("//*[contains(@class,'search-result') or contains(@class,'note-card')]")
    private val noResultsAlert = By.xpath("//*[contains(text(),'No notes found') or contains(text(),'No results')]")

    fun enterSearchQuery(query: String) {
        val el = WaitUtils.waitForVisible(driver, searchBarInput)
        el.clear()
        el.sendKeys(query)
        Thread.sleep(800) // wait for debounce
    }

    fun isSearchBarVisible() = WaitUtils.isPresent(driver, searchBarInput)
    fun isNoResultsVisible() = WaitUtils.isPresent(driver, noResultsAlert)
    fun getResultsCount() = driver.findElements(searchResults).size
}
