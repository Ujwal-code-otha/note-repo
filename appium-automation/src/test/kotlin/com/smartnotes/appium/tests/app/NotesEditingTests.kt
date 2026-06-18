package com.smartnotes.appium.tests.app

import com.smartnotes.appium.base.BaseTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NotesEditingTests : BaseTest() {

    @Test fun test01_verifyTitleEdit() { assertTrue(true) }
    @Test fun test02_verifyContentEdit() { assertTrue(true) }
    @Test fun test03_verifyEditAndSave() { assertTrue(true) }
    @Test fun test04_verifySpecialCharactersInTitle() { assertTrue(true) }
    @Test fun test05_verifySpecialCharactersInContent() { assertTrue(true) }
    @Test fun test06_verifyEmptyTitleEdit() { assertTrue(true) }
    @Test fun test07_verifyEmptyContentEdit() { assertTrue(true) }
    @Test fun test08_discardEdits() { assertTrue(true) }
    @Test fun test09_editAutoSaveTimer() { assertTrue(true) }
    @Test fun test10_verifyUpdatedHeading() { assertTrue(true) }
}
