package com.smartnotes.selenium.testdata

object TestData {

    // ── URLs ────────────────────────────────────────────────────────────────────
    const val BASE_URL      = "http://localhost:3000"
    const val LOGIN_URL     = "$BASE_URL/login"
    const val SIGNUP_URL    = "$BASE_URL/signup"
    const val DASHBOARD_URL = "$BASE_URL/dashboard"
    const val SETTINGS_URL  = "$BASE_URL/settings"

    // ── Valid credentials ───────────────────────────────────────────────────────
    const val VALID_EMAIL    = "shreyassatishkumar@gmail.com"
    const val VALID_PASSWORD = "123456"

    // ── Invalid credentials ─────────────────────────────────────────────────────
    const val INVALID_EMAIL    = "notauser@invalid.xyz"
    const val INVALID_PASSWORD = "wrongpassword"
    const val WRONG_FORMAT_EMAIL = "not-an-email"
    const val SHORT_PASSWORD   = "12"

    // ── Registration test data ──────────────────────────────────────────────────
    const val NEW_USER_EMAIL    = "testuser_auto_${System.currentTimeMillis()}@test.com"
    const val NEW_USER_PASSWORD = "Test@12345"
    const val NEW_USER_NAME     = "Automation User"

    // ── Note test data ──────────────────────────────────────────────────────────
    const val NOTE_TITLE_1   = "Selenium Automation Note"
    const val NOTE_TITLE_2   = "Performance Testing Concepts"
    const val NOTE_TITLE_3   = "Kotlin Testing Guide"
    const val NOTE_CONTENT_1 = "This note was created by the Selenium automation framework for SmartNotes AI."
    const val NOTE_CONTENT_2 = "Key performance metrics include FCP, LCP, TBT, CLS, and Speed Index."
    const val NOTE_CONTENT_3 = "Kotlin provides concise syntax and null-safety for test automation."
    const val EDIT_SUFFIX    = " (Edited)"
    const val SEARCH_TERM    = "Selenium"
    const val SEARCH_NO_RESULT = "xyznotenotetext999"

    // ── Page titles ─────────────────────────────────────────────────────────────
    const val APP_NAME       = "SmartNotes AI"

    // ── Timeouts ────────────────────────────────────────────────────────────────
    const val SHORT_WAIT   = 5L
    const val DEFAULT_WAIT = 20L
    const val LONG_WAIT    = 45L
}
