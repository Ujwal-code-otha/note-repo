package com.smartnotes.selenium.utils

import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFWorkbook
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
    private val reportDir = File(
        System.getProperty("selenium.report.dir") ?: "build/reports/selenium"
    ).also { it.mkdirs() }

    fun addRecord(record: TestRecord) {
        records.add(record)
        val icon = if (record.status == "PASS") "[PASS]" else "[FAIL]"
        println("  $icon ${record.testCase} (${record.duration}s)")
    }

    fun generateReport() {
        if (records.isEmpty()) {
            println("[ExcelReporter] No records — skipping report generation.")
            return
        }

        val wb = XSSFWorkbook()

        // ── Style helpers: cast explicitly to XSSFCellStyle ──────────────────
        fun headerStyle(bg: IndexedColors): XSSFCellStyle {
            val s = wb.createCellStyle() as XSSFCellStyle
            val f = wb.createFont()
            f.bold = true
            f.color = IndexedColors.WHITE.index
            f.fontHeightInPoints = 11
            s.setFont(f)
            s.fillForegroundColor = bg.index
            s.fillPattern = FillPatternType.SOLID_FOREGROUND
            s.alignment = HorizontalAlignment.CENTER
            s.borderBottom = BorderStyle.THIN
            return s
        }

        fun passStyle(): XSSFCellStyle {
            val s = wb.createCellStyle() as XSSFCellStyle
            s.fillForegroundColor = IndexedColors.LIGHT_GREEN.index
            s.fillPattern = FillPatternType.SOLID_FOREGROUND
            val f = wb.createFont()
            f.bold = true
            f.color = IndexedColors.DARK_GREEN.index
            s.setFont(f)
            s.alignment = HorizontalAlignment.CENTER
            return s
        }

        fun failStyle(): XSSFCellStyle {
            val s = wb.createCellStyle() as XSSFCellStyle
            s.fillForegroundColor = IndexedColors.ROSE.index
            s.fillPattern = FillPatternType.SOLID_FOREGROUND
            val f = wb.createFont()
            f.bold = true
            f.color = IndexedColors.RED.index
            s.setFont(f)
            s.alignment = HorizontalAlignment.CENTER
            return s
        }

        fun normalStyle(): XSSFCellStyle {
            val s = wb.createCellStyle() as XSSFCellStyle
            s.borderBottom = BorderStyle.HAIR
            return s
        }

        fun titleStyle(): XSSFCellStyle {
            val s = wb.createCellStyle() as XSSFCellStyle
            val f = wb.createFont()
            f.bold = true
            f.fontHeightInPoints = 14
            s.setFont(f)
            return s
        }

        val hdrBlue   = headerStyle(IndexedColors.DARK_BLUE)
        val hdrGreen  = headerStyle(IndexedColors.DARK_GREEN)
        val hdrOrange = headerStyle(IndexedColors.ORANGE)
        val passS     = passStyle()
        val failS     = failStyle()
        val normS     = normalStyle()
        val titleS    = titleStyle()

        // ══════════════════════════════════════════════════════════════════════
        // SHEET 1 — All 200 Test Results
        // ══════════════════════════════════════════════════════════════════════
        val sheet1 = wb.createSheet("Selenium Test Results")

        // Title row
        val titleRow  = sheet1.createRow(0)
        val titleCell = titleRow.createCell(0)
        titleCell.setCellValue("SmartNotes AI - Selenium E2E Test Report (350 Test Cases)")
        titleCell.cellStyle = titleS
        sheet1.addMergedRegion(CellRangeAddress(0, 0, 0, 5))

        // Generated-at row
        val genRow = sheet1.createRow(1)
        genRow.createCell(0).setCellValue(
            "Generated: ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}"
        )
        sheet1.addMergedRegion(CellRangeAddress(1, 1, 0, 5))

        // Header
        val hdrs1 = listOf("#", "Test Case", "Category", "Status", "Duration (s)", "Timestamp")
        val hdr1  = sheet1.createRow(2)
        hdrs1.forEachIndexed { i, h ->
            val c = hdr1.createCell(i)
            c.setCellValue(h)
            c.cellStyle = hdrBlue
        }

        // Data rows
        records.forEachIndexed { idx, r ->
            val row = sheet1.createRow(idx + 3)
            val c0 = row.createCell(0); c0.setCellValue((idx + 1).toDouble()); c0.cellStyle = normS
            val c1 = row.createCell(1); c1.setCellValue(r.testCase);           c1.cellStyle = normS
            val c2 = row.createCell(2); c2.setCellValue(r.category);           c2.cellStyle = normS
            val c3 = row.createCell(3)
            c3.setCellValue(r.status)
            c3.cellStyle = if (r.status == "PASS") passS else failS
            val c4 = row.createCell(4); c4.setCellValue(r.duration);           c4.cellStyle = normS
            val c5 = row.createCell(5); c5.setCellValue(r.timestamp);          c5.cellStyle = normS
        }
        listOf(5, 55, 16, 8, 13, 22).forEachIndexed { i, w -> sheet1.setColumnWidth(i, w * 256) }

        // ══════════════════════════════════════════════════════════════════════
        // SHEET 2 — Summary Dashboard
        // ══════════════════════════════════════════════════════════════════════
        val sheet2  = wb.createSheet("Summary")
        val passed  = records.count { it.status == "PASS" }
        val failed  = records.count { it.status == "FAIL" }
        val total   = records.size
        val pct     = if (total > 0) passed * 100.0 / total else 0.0
        val avgDur  = if (total > 0) records.sumOf { it.duration } / total else 0.0
        val totalDur= records.sumOf { it.duration }

        val sumTitle = sheet2.createRow(0)
        val stCell   = sumTitle.createCell(0)
        stCell.setCellValue("SmartNotes AI - Selenium Test Summary")
        stCell.cellStyle = titleS
        sheet2.addMergedRegion(CellRangeAddress(0, 0, 0, 2))

        val hdr2 = sheet2.createRow(1)
        listOf("Metric", "Value", "Status").forEachIndexed { i, h ->
            val c = hdr2.createCell(i)
            c.setCellValue(h)
            c.cellStyle = hdrGreen
        }

        val sumRows = listOf(
            Triple("Total Test Cases",     total.toString(),               ""),
            Triple("Passed",               passed.toString(),              "PASS"),
            Triple("Failed",               failed.toString(),              if (failed > 0) "FAIL" else ""),
            Triple("Pass Rate",            "%.1f%%".format(pct),          if (pct == 100.0) "PASS" else if (pct >= 90) "" else "FAIL"),
            Triple("Average Duration (s)", "%.2f".format(avgDur),         ""),
            Triple("Total Duration (s)",   "%.2f".format(totalDur),       ""),
            Triple("Overall Result",       if (failed == 0) "PASS" else "FAIL", if (failed == 0) "PASS" else "FAIL")
        )
        sumRows.forEachIndexed { i, (label, value, status) ->
            val row = sheet2.createRow(i + 2)
            row.createCell(0).setCellValue(label)
            row.createCell(1).setCellValue(value)
            val sc = row.createCell(2)
            sc.setCellValue(status)
            sc.cellStyle = when (status) { "PASS" -> passS; "FAIL" -> failS; else -> normS }
        }
        sheet2.setColumnWidth(0, 26 * 256)
        sheet2.setColumnWidth(1, 16 * 256)
        sheet2.setColumnWidth(2, 12 * 256)

        // ══════════════════════════════════════════════════════════════════════
        // SHEET 3 — Category Breakdown
        // ══════════════════════════════════════════════════════════════════════
        val sheet3 = wb.createSheet("Category Breakdown")
        val hdr3 = sheet3.createRow(0)
        listOf("Category", "Total", "Passed", "Failed", "Pass Rate").forEachIndexed { i, h ->
            val c = hdr3.createCell(i)
            c.setCellValue(h)
            c.cellStyle = hdrOrange
        }
        records.groupBy { it.category }.entries.forEachIndexed { i, (cat, items) ->
            val p  = items.count { it.status == "PASS" }
            val f  = items.count { it.status == "FAIL" }
            val pc = if (items.isNotEmpty()) p * 100.0 / items.size else 0.0
            val row = sheet3.createRow(i + 1)
            row.createCell(0).setCellValue(cat)
            row.createCell(1).setCellValue(items.size.toDouble())
            val pc2 = row.createCell(2); pc2.setCellValue(p.toDouble()); pc2.cellStyle = if (p > 0) passS else normS
            val fc  = row.createCell(3); fc.setCellValue(f.toDouble());  fc.cellStyle  = if (f > 0) failS else normS
            row.createCell(4).setCellValue("%.1f%%".format(pc))
        }
        listOf(20, 10, 10, 10, 12).forEachIndexed { i, w -> sheet3.setColumnWidth(i, w * 256) }

        // ── Write ─────────────────────────────────────────────────────────────
        val ts   = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
        val file = File(reportDir, "Selenium_Test_Report_$ts.xlsx")
        FileOutputStream(file).use { wb.write(it) }
        wb.close()

        println("\n" + "=".repeat(65))
        println("  Excel Report -> ${file.absolutePath}")
        println("  Total=$total | Passed=$passed | Failed=$failed")
        println("  Pass Rate = ${"%.1f".format(pct)}%")
        println("=".repeat(65))
    }
}
