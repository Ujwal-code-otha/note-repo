package com.smartnotes.appium.base

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler
import org.junit.jupiter.api.extension.BeforeEachCallback

class RetryExtension : TestExecutionExceptionHandler, BeforeEachCallback {

    companion object {
        private const val MAX_RETRIES = 2
        private val retryCount = ThreadLocal.withInitial { 0 }
    }

    override fun beforeEach(context: ExtensionContext) {
        retryCount.set(0)
    }

    override fun handleTestExecutionException(context: ExtensionContext, throwable: Throwable) {
        val current = retryCount.get()
        if (current < MAX_RETRIES) {
            retryCount.set(current + 1)
            println("[RETRY] Appium attempt ${current + 1} of $MAX_RETRIES for: ${context.displayName}")
        }
        throw throwable
    }
}
