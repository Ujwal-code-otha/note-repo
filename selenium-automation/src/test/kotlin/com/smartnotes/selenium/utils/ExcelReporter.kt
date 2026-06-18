package com.smartnotes.selenium.utils

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.ss.usermodel.*
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.CopyOnWriteArrayList

data class TestRecord(
    val testCase: String,
    val category: String,
    val status: String,
    val duration: Double,
    val details: String,
    val timestamp: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
)

object ExcelReporter {

    private val records = CopyOnWriteArrayList<TestRecord>()
    private val reportDir = File("build/reports/selenium").also { it.mkdirs() }

    fun addRecord(record: TestRecord) {
        records.add(record)
        val icon = if (record.status == "PASS") "✅" else "❌"
        println("$icon [${record.status}] ${record.testCase} (${record.duration}s)")
    }

    fun generateReport() {
        val wb = XSSFWorkbook()

        // ── Detail Sheet ──────────────────────────────────────────────────────
        val detailSheet = wb.createSheet("Test Results")
        val headers = listOf("Test Case", "Category", "Status", "Duration (s)", "Details", "Timestamp")
        val headerRow = detailSheet.createRow(0)
        val headerStyle = wb.createCellStyle().apply {
            val font = wb.createFont().apply {
                bold = true
                color = IndexedColors.WHITE.index
            }
            setFont(font)
            fillForegroundColor = IndexedColors.DARK_BLUE.index
            fillPattern = FillPatternType.SOLID_FOREGROUND
        }
        headers.forEachIndexed { i, h ->
            headerRow.createCell(i).apply { setCellValue(h); cellStyle = headerStyle }
        }
        records.forEachIndexed { i, r ->
            val row = detailSheet.createRow(i + 1)
            row.createCell(0).setCellValue(r.testCase)
            row.createCell(1).setCellValue(r.category)
            row.createCell(2).setCellValue(r.status)
            row.createCell(3).setCellValue(r.duration)
            row.createCell(4).setCellValue(r.details)
            row.createCell(5).setCellValue(r.timestamp)
        }
        (0..5).forEach { detailSheet.autoSizeColumn(it) }

        // ── Summary Sheet ─────────────────────────────────────────────────────
        val summarySheet = wb.createSheet("Summary")
        val passed = records.count { it.status == "PASS" }
        val failed = records.count { it.status == "FAIL" }
        val total  = records.size
        val pct    = if (total > 0) (passed * 100.0 / total) else 0.0
        val avgDur = if (total > 0) records.sumOf { it.duration } / total else 0.0

        val summaryData = listOf(
            listOf("Total Test Cases", total.toString()),
            listOf("Passed", passed.toString()),
            listOf("Failed", failed.toString()),
            listOf("Pass Percentage", "%.1f%%".format(pct)),
            listOf("Average Duration (s)", "%.2f".format(avgDur)),
            listOf("Overall Status", if (failed == 0) "PASS" else "FAIL")
        )
        summaryData.forEachIndexed { i, row ->
            val r = summarySheet.createRow(i)
            r.createCell(0).setCellValue(row[0])
            r.createCell(1).setCellValue(row[1])
        }
        summarySheet.autoSizeColumn(0); summarySheet.autoSizeColumn(1)

        // ── Write ─────────────────────────────────────────────────────────────
        val ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
        val file = File(reportDir, "Selenium_Test_Report_$ts.xlsx")
        FileOutputStream(file).use { wb.write(it) }
        wb.close()
        println("\n📊 Excel report → ${file.absolutePath}")
        println("   Total=$total | Passed=$passed | Failed=$failed | Pass=${"%.1f".format(pct)}%")
    }
}
