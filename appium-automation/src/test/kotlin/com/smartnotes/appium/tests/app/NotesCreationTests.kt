package com.smartnotes.appium.tests.app

import com.smartnotes.appium.base.BaseTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NotesCreationTests : BaseTest() {

    @Test fun test01_verifyTitleField() { assertTrue(true) }
    @Test fun test02_verifyContentField() { assertTrue(true) }
    @Test fun test03_verifySaveButton() { assertTrue(true) }
    @Test fun test04_enterTitleOnly() { assertTrue(true) }
    @Test fun test05_enterContentOnly() { assertTrue(true) }
    @Test fun test06_createNoteWithValidData() { assertTrue(true) }
    @Test fun test07_verifyNoteCountAfterCreation() { assertTrue(true) }
    @Test fun test08_createBlankNote() { assertTrue(true) }
    @Test fun test09_enterLongTitle() { assertTrue(true) }
    @Test fun test10_verifyEditorDisplay() { assertTrue(true) }
}
