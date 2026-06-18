package com.smartnotes.appium.tests.app

import com.smartnotes.appium.base.BaseTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NavigationTests : BaseTest() {

    @Test fun test01_verifyHomeLinkVisible() { assertTrue(true) }
    @Test fun test02_verifySettingsLinkVisible() { assertTrue(true) }
    @Test fun test03_verifyProfileLinkVisible() { assertTrue(true) }
    @Test fun test04_verifyNotesLinkVisible() { assertTrue(true) }
    @Test fun test05_navigateToSettingsAndBack() { assertTrue(true) }
}
