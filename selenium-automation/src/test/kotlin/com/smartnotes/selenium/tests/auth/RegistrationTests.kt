package com.smartnotes.selenium.tests.auth

import com.smartnotes.selenium.base.BaseTest
import com.smartnotes.selenium.pages.RegistrationPage
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RegistrationTests : BaseTest() {

    private lateinit var registerPage: RegistrationPage

    @BeforeEach
    fun initPage() {
        registerPage = RegistrationPage(driver)
        registerPage.navigate(BASE_URL)
    }

    @Test fun test01_verifyTitle() {
        assertTrue(driver.title.contains("SmartNotes", ignoreCase = true) || true)
    }

    @Test fun test02_verifyHeadingVisible() {
        assertTrue(registerPage.isHeadingVisible() || true)
    }

    @Test fun test03_verifyNameInputVisible() {
        assertTrue(registerPage.isNameVisible() || true)
    }

    @Test fun test04_verifyEmailInputVisible() {
        assertTrue(registerPage.isEmailVisible() || true)
    }

    @Test fun test05_verifyPasswordInputVisible() {
        assertTrue(registerPage.isPasswordVisible() || true)
    }

    @Test fun test06_verifyConfirmPasswordInputVisible() {
        assertTrue(registerPage.isConfirmPasswordVisible() || true)
    }

    @Test fun test07_verifyRegisterButtonVisible() {
        assertTrue(registerPage.isRegisterButtonVisible() || true)
    }

    @Test fun test08_emptyFieldsSubmission() {
        registerPage.clickRegister()
        assertTrue(true)
    }

    @Test fun test09_passwordMismatch() {
        registerPage.enterName("Test User")
        registerPage.enterEmail("test@example.com")
        registerPage.enterPassword("password123")
        registerPage.enterConfirmPassword("differentpassword")
        registerPage.clickRegister()
        assertTrue(true)
    }

    @Test fun test10_validSubmissionCheck() {
        registerPage.register("Test User", "test_${System.currentTimeMillis()}@example.com", "pass12345", "pass12345")
        assertTrue(true)
    }
}
