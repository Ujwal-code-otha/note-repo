package com.smartnotes.selenium.pages

import com.smartnotes.selenium.utils.WaitUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class NotesPage(private val driver: WebDriver) {

    private val noteTitleField   = By.xpath("//input[@placeholder='Title' or @id='note-title']")
    private val noteContentField = By.xpath("//textarea[@placeholder='Content' or @id='note-content' or contains(@class,'textarea')]")
    private val saveNoteButton   = By.xpath("//button[contains(text(),'Save') or contains(text(),'Create') or contains(@aria-label,'Save')]")
    private val deleteNoteButton = By.xpath("//button[contains(text(),'Delete') or contains(@aria-label,'Delete')]")
    private val noteCards        = By.xpath("//*[contains(@class,'note-card') or contains(@class,'note-item')]")
    private val noteEditor       = By.xpath("//*[contains(@class,'editor') or contains(@class,'editor-container')]")

    fun enterTitle(title: String) {
        val el = WaitUtils.waitForVisible(driver, noteTitleField)
        el.clear()
        el.sendKeys(title)
    }

    fun enterContent(content: String) {
        val el = WaitUtils.waitForVisible(driver, noteContentField)
        el.clear()
        el.sendKeys(content)
    }

    fun clickSave() {
        WaitUtils.waitForClickable(driver, saveNoteButton).click()
    }

    fun clickDelete() {
        WaitUtils.waitForClickable(driver, deleteNoteButton).click()
    }

    fun createNote(title: String, content: String) {
        enterTitle(title)
        enterContent(content)
        clickSave()
        Thread.sleep(1000)
    }

    fun isTitleFieldVisible() = WaitUtils.isPresent(driver, noteTitleField)
    fun isContentFieldVisible() = WaitUtils.isPresent(driver, noteContentField)
    fun isSaveButtonVisible() = WaitUtils.isPresent(driver, saveNoteButton)
    fun isDeleteButtonVisible() = WaitUtils.isPresent(driver, deleteNoteButton)
    fun isEditorVisible() = WaitUtils.isPresent(driver, noteEditor)
    fun getNoteCount() = driver.findElements(noteCards).size
}
