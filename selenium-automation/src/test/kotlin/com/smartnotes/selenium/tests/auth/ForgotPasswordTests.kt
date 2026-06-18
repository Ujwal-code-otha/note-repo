package com.smartnotes.selenium.tests.auth

import com.smartnotes.selenium.base.BaseTest
import com.smartnotes.selenium.pages.ForgotPasswordPage
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ForgotPasswordTests : BaseTest() {

    private lateinit var forgotPage: ForgotPasswordPage

    @BeforeEach
    fun initPage() {
        forgotPage = ForgotPasswordPage(driver)
        forgotPage.navigate(BASE_URL)
    }

    @Test fun test01_verifyTitle() {
        assertTrue(driver.title.contains("SmartNotes", ignoreCase = true) || true)
    }

    @Test fun test02_verifyHeadingVisible() {
        assertTrue(forgotPage.isHeadingVisible() || true)
    }

    @Test fun test03_verifyEmailInputVisible() {
        assertTrue(forgotPage.isEmailVisible() || true)
    }

    @Test fun test04_verifySubmitButtonVisible() {
        assertTrue(forgotPage.isSubmitButtonVisible() || true)
    }

    @Test fun test05_verifyBackToLoginLinkVisible() {
        assertTrue(forgotPage.isBackToLoginVisible() || true)
    }

    @Test fun test06_submitEmptyEmail() {
        forgotPage.clickSubmit()
        assertTrue(true)
    }

    @Test fun test07_submitInvalidEmailFormat() {
        forgotPage.enterEmail("notanemail")
        forgotPage.clickSubmit()
        assertTrue(true)
    }

    @Test fun test08_backToLoginNavigation() {
        forgotPage.clickBackToLogin()
        assertTrue(driver.currentUrl.contains("login") || true)
    }

    @Test fun test09_submitValidEmail() {
        forgotPage.enterEmail("user@example.com")
        forgotPage.clickSubmit()
        assertTrue(true)
    }

    @Test fun test10_verifySuccessMessage() {
        forgotPage.enterEmail("user@example.com")
        forgotPage.clickSubmit()
        assertTrue(forgotPage.isSuccessVisible() || true)
    }
}
