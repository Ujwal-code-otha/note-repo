package com.smartnotes.appium.utils

import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
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

        // ── Styles ──────────────────────────────────────────────────────────────
        fun headerStyle(fg: IndexedColors): CellStyle = wb.createCellStyle().apply {
            val font = wb.createFont().apply { bold = true; color = IndexedColors.WHITE.index; fontHeightInPoints = 11 }
            setFont(font)
            fillForegroundColor = fg.index
            fillPattern = FillPatternType.SOLID_FOREGROUND
            alignment = HorizontalAlignment.CENTER
            borderBottom = BorderStyle.THIN
        }

        fun passStyle(): CellStyle = wb.createCellStyle().apply {
            fillForegroundColor = IndexedColors.LIGHT_GREEN.index
            fillPattern = FillPatternType.SOLID_FOREGROUND
            val font = wb.createFont().apply { bold = true; color = IndexedColors.DARK_GREEN.index }
            setFont(font)
            alignment = HorizontalAlignment.CENTER
        }

        fun failStyle(): CellStyle = wb.createCellStyle().apply {
            fillForegroundColor = IndexedColors.ROSE.index
            fillPattern = FillPatternType.SOLID_FOREGROUND
            val font = wb.createFont().apply { bold = true; color = IndexedColors.RED.index }
            setFont(font)
            alignment = HorizontalAlignment.CENTER
        }

        fun skipStyle(): CellStyle = wb.createCellStyle().apply {
            fillForegroundColor = IndexedColors.LIGHT_YELLOW.index
            fillPattern = FillPatternType.SOLID_FOREGROUND
            alignment = HorizontalAlignment.CENTER
        }

        fun normalStyle(): CellStyle = wb.createCellStyle().apply {
            borderBottom = BorderStyle.HAIR
        }

        val hdrGreen  = headerStyle(IndexedColors.DARK_GREEN)
        val hdrBlue   = headerStyle(IndexedColors.ROYAL_BLUE)
        val hdrOrange = headerStyle(IndexedColors.ORANGE)
        val passS     = passStyle()
        val failS     = failStyle()
        val skipS     = skipStyle()
        val normS     = normalStyle()

        // ══════════════════════════════════════════════════════════════════════
        // SHEET 1 — Full Test Results (200 rows)
        // ══════════════════════════════════════════════════════════════════════
        val sheet1 = wb.createSheet("Appium Test Results")
        val cols1  = listOf("#", "Test Case", "Category", "Status", "Duration (s)", "Details", "Timestamp")

        // Title row
        val titleRow = sheet1.createRow(0)
        val titleCell = titleRow.createCell(0)
        titleCell.setCellValue("SmartNotes AI — Appium E2E Test Report (200 Test Cases)")
        titleCell.cellStyle = wb.createCellStyle().apply {
            val f = wb.createFont().apply { bold = true; fontHeightInPoints = 14 }
            setFont(f)
        }
        sheet1.addMergedRegion(CellRangeAddress(0, 0, 0, cols1.size - 1))

        // Generated at row
        val genRow = sheet1.createRow(1)
        genRow.createCell(0).setCellValue(
            "Generated: ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}"
        )
        sheet1.addMergedRegion(CellRangeAddress(1, 1, 0, cols1.size - 1))

        // Header row
        val hdr1 = sheet1.createRow(2)
        cols1.forEachIndexed { i, h ->
            hdr1.createCell(i).apply { setCellValue(h); cellStyle = hdrGreen }
        }

        // Data rows
        records.forEachIndexed { idx, r ->
            val row = sheet1.createRow(idx + 3)
            row.createCell(0).apply { setCellValue((idx + 1).toDouble()); cellStyle = normS }
            row.createCell(1).apply { setCellValue(r.testCase);  cellStyle = normS }
            row.createCell(2).apply { setCellValue(r.category);  cellStyle = normS }
            row.createCell(3).apply {
                setCellValue(r.status)
                cellStyle = when (r.status) { "PASS" -> passS; "FAIL" -> failS; else -> skipS }
            }
            row.createCell(4).apply { setCellValue(r.duration);  cellStyle = normS }
            row.createCell(5).apply { setCellValue(r.details);   cellStyle = normS }
            row.createCell(6).apply { setCellValue(r.timestamp); cellStyle = normS }
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

        val sumTitle = sheet2.createRow(0)
        sumTitle.createCell(0).apply {
            setCellValue("SmartNotes AI — Test Summary Dashboard")
            cellStyle = wb.createCellStyle().apply {
                val f = wb.createFont().apply { bold = true; fontHeightInPoints = 14 }
                setFont(f)
            }
        }
        sheet2.addMergedRegion(CellRangeAddress(0, 0, 0, 2))

        val hdr2 = sheet2.createRow(1)
        listOf("Metric", "Value", "Status").forEachIndexed { i, h ->
            hdr2.createCell(i).apply { setCellValue(h); cellStyle = hdrBlue }
        }

        val summaryRows = listOf(
            Triple("Total Test Cases",    total.toString(),              ""),
            Triple("Passed",              passed.toString(),             "PASS"),
            Triple("Failed",              failed.toString(),             if (failed > 0) "FAIL" else ""),
            Triple("Skipped",             skipped.toString(),            ""),
            Triple("Pass Rate",           "%.1f%%".format(pct),          if (pct == 100.0) "PASS" else if (pct >= 90) "WARN" else "FAIL"),
            Triple("Average Duration (s)","%.2f".format(avgDur),         ""),
            Triple("Total Duration (s)",  "%.2f".format(totalDur),       ""),
            Triple("Overall Result",      if (failed == 0) "PASS" else "FAIL", if (failed == 0) "PASS" else "FAIL")
        )
        summaryRows.forEachIndexed { i, (label, value, status) ->
            val row = sheet2.createRow(i + 2)
            row.createCell(0).setCellValue(label)
            row.createCell(1).setCellValue(value)
            val statusCell = row.createCell(2)
            statusCell.setCellValue(status)
            statusCell.cellStyle = when (status) { "PASS" -> passS; "FAIL" -> failS; else -> normS }
        }
        sheet2.setColumnWidth(0, 26 * 256); sheet2.setColumnWidth(1, 16 * 256); sheet2.setColumnWidth(2, 12 * 256)

        // ══════════════════════════════════════════════════════════════════════
        // SHEET 3 — Category Breakdown
        // ══════════════════════════════════════════════════════════════════════
        val sheet3 = wb.createSheet("Category Breakdown")
        val hdr3 = sheet3.createRow(0)
        listOf("Category", "Total", "Passed", "Failed", "Pass Rate").forEachIndexed { i, h ->
            hdr3.createCell(i).apply { setCellValue(h); cellStyle = hdrOrange }
        }
        val byCategory = records.groupBy { it.category }
        byCategory.entries.forEachIndexed { i, (cat, items) ->
            val p  = items.count { it.status == "PASS" }
            val f  = items.count { it.status == "FAIL" }
            val pc = if (items.isNotEmpty()) p * 100.0 / items.size else 0.0
            val row = sheet3.createRow(i + 1)
            row.createCell(0).setCellValue(cat)
            row.createCell(1).setCellValue(items.size.toDouble())
            row.createCell(2).apply { setCellValue(p.toDouble()); cellStyle = if (p > 0) passS else normS }
            row.createCell(3).apply { setCellValue(f.toDouble()); cellStyle = if (f > 0) failS else normS }
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
