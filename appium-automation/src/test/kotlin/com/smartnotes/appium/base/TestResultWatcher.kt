package com.smartnotes.appium.base

import com.smartnotes.appium.utils.ExcelReporter
import com.smartnotes.appium.utils.TestRecord
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import java.util.Optional

class TestResultWatcher : TestWatcher, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback {

    companion object {
        private val startTime = ThreadLocal<Long>()
    }

    override fun beforeTestExecution(context: ExtensionContext) {
        startTime.set(System.currentTimeMillis())
    }

    override fun afterTestExecution(context: ExtensionContext) {
        // Done
    }

    override fun testSuccessful(context: ExtensionContext) {
        val duration = (System.currentTimeMillis() - (startTime.get() ?: System.currentTimeMillis())) / 1000.0
        val className = context.requiredTestClass.simpleName
        ExcelReporter.addRecord(
            TestRecord(
                testCase = context.displayName,
                category = className.replace("Tests", ""),
                status = "PASS",
                duration = duration,
                details = "Appium test passed successfully"
            )
        )
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable) {
        val duration = (System.currentTimeMillis() - (startTime.get() ?: System.currentTimeMillis())) / 1000.0
        val className = context.requiredTestClass.simpleName
        ExcelReporter.addRecord(
            TestRecord(
                testCase = context.displayName,
                category = className.replace("Tests", ""),
                status = "FAIL",
                duration = duration,
                details = cause.message ?: "Appium test failed"
            )
        )
    }

    override fun afterAll(context: ExtensionContext) {
        ExcelReporter.generateReport()
    }
}
