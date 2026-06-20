package com.smartnotes.appium.base

import com.smartnotes.appium.utils.ExcelReporter
import com.smartnotes.appium.utils.TestRecord
import org.junit.jupiter.api.extension.*
import java.util.Optional
import java.util.concurrent.atomic.AtomicInteger

/**
 * JUnit 5 extension that:
 *  1. Times each test
 *  2. Records PASS/FAIL to ExcelReporter
 *  3. Generates the Excel report exactly once when ALL tests are done
 */
class TestResultWatcher : TestWatcher, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback {

    companion object {
        private val startTime = ThreadLocal<Long>()
        // Track how many test classes have finished to generate report once
        private val classesFinished = AtomicInteger(0)
        private val reportGenerated = java.util.concurrent.atomic.AtomicBoolean(false)
    }

    override fun beforeTestExecution(context: ExtensionContext) {
        startTime.set(System.currentTimeMillis())
    }

    override fun afterTestExecution(context: ExtensionContext) {
        // nothing extra needed
    }

    override fun testSuccessful(context: ExtensionContext) {
        val duration = (System.currentTimeMillis() - (startTime.get() ?: System.currentTimeMillis())) / 1000.0
        ExcelReporter.addRecord(
            TestRecord(
                testCase  = context.displayName.replace("`", "").trim(),
                category  = resolveCategory(context),
                status    = "PASS",
                duration  = duration,
                details   = "Passed successfully"
            )
        )
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable) {
        val duration = (System.currentTimeMillis() - (startTime.get() ?: System.currentTimeMillis())) / 1000.0
        ExcelReporter.addRecord(
            TestRecord(
                testCase  = context.displayName.replace("`", "").trim(),
                category  = resolveCategory(context),
                status    = "FAIL",
                duration  = duration,
                details   = cause.message ?: "Test failed"
            )
        )
    }

    override fun testDisabled(context: ExtensionContext, reason: Optional<String>) {
        ExcelReporter.addRecord(
            TestRecord(
                testCase  = context.displayName.replace("`", "").trim(),
                category  = resolveCategory(context),
                status    = "SKIP",
                duration  = 0.0,
                details   = reason.orElse("Disabled")
            )
        )
    }

    override fun afterAll(context: ExtensionContext) {
        // Generate report only once — after the root/parent context finishes
        if (context.parent.map { it.root == it }.orElse(true)) {
            if (reportGenerated.compareAndSet(false, true)) {
                ExcelReporter.generateReport()
            }
        }
    }

    private fun resolveCategory(context: ExtensionContext): String {
        val name = context.displayName
        return when {
            name.contains("TC-0") && name.substringAfter("TC-0").take(2).toIntOrNull()?.let { it <= 20 } == true -> "Launch"
            name.contains("TC-02") || name.contains("TC-03") || name.contains("TC-04") -> "Authentication"
            name.contains("TC-04") || name.contains("TC-05") || name.contains("TC-06") -> "Home"
            name.contains("TC-06") || name.contains("TC-07") || name.contains("TC-08") -> "FocusTimer"
            name.contains("TC-08") || name.contains("TC-09") || name.contains("TC-10") -> "Exams"
            name.contains("TC-10") || name.contains("TC-11") || name.contains("TC-12") -> "Planner"
            name.contains("TC-12") || name.contains("TC-13") || name.contains("TC-14") -> "Notes"
            name.contains("TC-14") || name.contains("TC-15") || name.contains("TC-16") -> "Profile"
            name.contains("TC-16") || name.contains("TC-17") || name.contains("TC-18") -> "Analytics"
            name.contains("TC-18") || name.contains("TC-19") || name.contains("TC-20") -> "Navigation"
            else -> context.requiredTestClass.simpleName.replace("Tests", "")
        }
    }
}
