package com.smartnotes.appium.utils

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
    private val reportDir = File("build/reports/appium").also { it.mkdirs() }

    fun addRecord(record: TestRecord) {
        records.add(record)
        val icon = when (record.status) {
            "PASS" -> "[PASS]"
            "FAIL" -> "[FAIL]"
            "SKIP" -> "[SKIP]"
            else   -> "[INFO]"
        }
        println("  $icon ${record.testCase} (${record.duration}s)")
    }

    fun generateReport() {
        if (records.isEmpty()) {
            println("[ExcelReporter] No records to write.")
            return
        }

        val wb = XSSFWorkbook()

        // ── Style helpers — explicitly return XSSFCellStyle to avoid type mismatch ──
        fun makeHeaderStyle(fg: IndexedColors): XSSFCellStyle {
            val style = wb.createCellStyle() as XSSFCellStyle
            val font = wb.createFont()
            font.bold = true
            font.color = IndexedColors.WHITE.index
            font.fontHeightInPoints = 11
            style.setFont(font)
            style.fillForegroundColor = fg.index
            style.fillPattern = FillPatternType.SOLID_FOREGROUND
            style.alignment = HorizontalAlignment.CENTER
            style.borderBottom = BorderStyle.THIN
            return style
        }

        fun makePassStyle(): XSSFCellStyle {
            val style = wb.createCellStyle() as XSSFCellStyle
            style.fillForegroundColor = IndexedColors.LIGHT_GREEN.index
            style.fillPattern = FillPatternType.SOLID_FOREGROUND
            val font = wb.createFont()
            font.bold = true
            font.color = IndexedColors.DARK_GREEN.index
            style.setFont(font)
            style.alignment = HorizontalAlignment.CENTER
            return style
        }

        fun makeFailStyle(): XSSFCellStyle {
            val style = wb.createCellStyle() as XSSFCellStyle
            style.fillForegroundColor = IndexedColors.ROSE.index
            style.fillPattern = FillPatternType.SOLID_FOREGROUND
            val font = wb.createFont()
            font.bold = true
            font.color = IndexedColors.RED.index
            style.setFont(font)
            style.alignment = HorizontalAlignment.CENTER
            return style
        }

        fun makeSkipStyle(): XSSFCellStyle {
            val style = wb.createCellStyle() as XSSFCellStyle
            style.fillForegroundColor = IndexedColors.LIGHT_YELLOW.index
            style.fillPattern = FillPatternType.SOLID_FOREGROUND
            style.alignment = HorizontalAlignment.CENTER
            return style
        }

        fun makeNormalStyle(): XSSFCellStyle {
            val style = wb.createCellStyle() as XSSFCellStyle
            style.borderBottom = BorderStyle.HAIR
            return style
        }

        fun makeTitleStyle(): XSSFCellStyle {
            val style = wb.createCellStyle() as XSSFCellStyle
            val font = wb.createFont()
            font.bold = true
            font.fontHeightInPoints = 14
            style.setFont(font)
            return style
        }

        val hdrGreen  = makeHeaderStyle(IndexedColors.DARK_GREEN)
        val hdrBlue   = makeHeaderStyle(IndexedColors.ROYAL_BLUE)
        val hdrOrange = makeHeaderStyle(IndexedColors.ORANGE)
        val passS     = makePassStyle()
        val failS     = makeFailStyle()
        val skipS     = makeSkipStyle()
        val normS     = makeNormalStyle()
        val titleS    = makeTitleStyle()

        // ══════════════════════════════════════════════════════════════════════
        // SHEET 1 — Full Test Results (200 rows)
        // ══════════════════════════════════════════════════════════════════════
        val sheet1 = wb.createSheet("Appium Test Results")
        val cols1  = listOf("#", "Test Case", "Category", "Status", "Duration (s)", "Details", "Timestamp")

        // Title row
        val titleRow  = sheet1.createRow(0)
        val titleCell = titleRow.createCell(0)
        titleCell.setCellValue("SmartNotes AI - Appium E2E Test Report (200 Test Cases)")
        titleCell.cellStyle = titleS
        sheet1.addMergedRegion(CellRangeAddress(0, 0, 0, cols1.size - 1))

        // Generated-at row
        val genRow = sheet1.createRow(1)
        genRow.createCell(0).setCellValue(
            "Generated: ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}"
        )
        sheet1.addMergedRegion(CellRangeAddress(1, 1, 0, cols1.size - 1))

        // Header row
        val hdr1 = sheet1.createRow(2)
        cols1.forEachIndexed { i, h ->
            val cell = hdr1.createCell(i)
            cell.setCellValue(h)
            cell.cellStyle = hdrGreen
        }

        // Data rows
        records.forEachIndexed { idx, r ->
            val row = sheet1.createRow(idx + 3)

            val c0 = row.createCell(0); c0.setCellValue((idx + 1).toDouble()); c0.cellStyle = normS
            val c1 = row.createCell(1); c1.setCellValue(r.testCase);           c1.cellStyle = normS
            val c2 = row.createCell(2); c2.setCellValue(r.category);           c2.cellStyle = normS
            val c3 = row.createCell(3)
            c3.setCellValue(r.status)
            c3.cellStyle = when (r.status) { "PASS" -> passS; "FAIL" -> failS; else -> skipS }
            val c4 = row.createCell(4); c4.setCellValue(r.duration);           c4.cellStyle = normS
            val c5 = row.createCell(5); c5.setCellValue(r.details);            c5.cellStyle = normS
            val c6 = row.createCell(6); c6.setCellValue(r.timestamp);          c6.cellStyle = normS
        }
        listOf(4, 30, 16, 8, 13, 50, 22).forEachIndexed { i, w -> sheet1.setColumnWidth(i, w * 256) }

        // ══════════════════════════════════════════════════════════════════════
        // SHEET 2 — Summary Dashboard
        // ══════════════════════════════════════════════════════════════════════
        val sheet2  = wb.createSheet("Summary")
        val passed  = records.count { it.status == "PASS" }
        val failed  = records.count { it.status == "FAIL" }
        val skipped = records.count { it.status == "SKIP" }
        val total   = records.size
        val pct     = if (total > 0) passed * 100.0 / total else 0.0
        val avgDur  = if (total > 0) records.sumOf { it.duration } / total else 0.0
        val totalDur= records.sumOf { it.duration }

        val sumTitleRow = sheet2.createRow(0)
        val sumTitleCell = sumTitleRow.createCell(0)
        sumTitleCell.setCellValue("SmartNotes AI - Test Summary Dashboard")
        sumTitleCell.cellStyle = titleS
        sheet2.addMergedRegion(CellRangeAddress(0, 0, 0, 2))

        val hdr2 = sheet2.createRow(1)
        listOf("Metric", "Value", "Status").forEachIndexed { i, h ->
            val cell = hdr2.createCell(i)
            cell.setCellValue(h)
            cell.cellStyle = hdrBlue
        }

        val summaryRows = listOf(
            Triple("Total Test Cases",     total.toString(),                 ""),
            Triple("Passed",               passed.toString(),                "PASS"),
            Triple("Failed",               failed.toString(),                if (failed > 0) "FAIL" else ""),
            Triple("Skipped",              skipped.toString(),               ""),
            Triple("Pass Rate",            "%.1f%%".format(pct),             if (pct == 100.0) "PASS" else if (pct >= 90) "" else "FAIL"),
            Triple("Average Duration (s)", "%.2f".format(avgDur),            ""),
            Triple("Total Duration (s)",   "%.2f".format(totalDur),          ""),
            Triple("Overall Result",       if (failed == 0) "PASS" else "FAIL", if (failed == 0) "PASS" else "FAIL")
        )
        summaryRows.forEachIndexed { i, (label, value, status) ->
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
            val cell = hdr3.createCell(i)
            cell.setCellValue(h)
            cell.cellStyle = hdrOrange
        }
        val byCategory = records.groupBy { it.category }
        byCategory.entries.forEachIndexed { i, (cat, items) ->
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

        // ── Write file ────────────────────────────────────────────────────────
        val ts   = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
        val file = File(reportDir, "SmartNotes_Appium_Report_$ts.xlsx")
        FileOutputStream(file).use { wb.write(it) }
        wb.close()

        println("\n" + "=".repeat(65))
        println("  Excel Report -> ${file.absolutePath}")
        println("  Total=$total | Passed=$passed | Failed=$failed | Skipped=$skipped")
        println("  Pass Rate = ${"%.1f".format(pct)}%")
        println("=".repeat(65))
    }
}
