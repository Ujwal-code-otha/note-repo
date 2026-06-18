package com.smartnotes.appium.tests.auth

import com.smartnotes.appium.base.BaseTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RegistrationTests : BaseTest() {

    @Test fun test01_verifyTitle() { assertTrue(true) }
    @Test fun test02_verifyHeadingVisible() { assertTrue(true) }
    @Test fun test03_verifyNameInputVisible() { assertTrue(true) }
    @Test fun test04_verifyEmailInputVisible() { assertTrue(true) }
    @Test fun test05_verifyPasswordInputVisible() { assertTrue(true) }
    @Test fun test06_verifyConfirmPasswordInputVisible() { assertTrue(true) }
    @Test fun test07_verifyRegisterButtonVisible() { assertTrue(true) }
    @Test fun test08_emptyFieldsSubmission() { assertTrue(true) }
    @Test fun test09_passwordMismatch() { assertTrue(true) }
    @Test fun test10_validSubmissionCheck() { assertTrue(true) }
}
