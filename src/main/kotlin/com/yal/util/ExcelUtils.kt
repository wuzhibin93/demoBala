package com.enlink.es.common

import org.apache.poi.xssf.streaming.SXSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import kotlin.system.measureTimeMillis

/**
 * 生成文件
 */
fun createExcel(filePath: String, headers: Map<String, String>, dataArrays: List<Map<String, Any>>) {
    println("开始写入文件.......")
    val expose_time = measureTimeMillis {
        var xwb: SXSSFWorkbook = SXSSFWorkbook(10000)
        var startIndex: Int = 0
        val f = File(filePath)
        if (f.exists()) {
            val wb = XSSFWorkbook(f.inputStream())
            startIndex = wb.getSheet("sheet1").lastRowNum
            xwb = SXSSFWorkbook(wb, 10000)
        } else {
            f.parentFile.mkdirs()
            f.createNewFile()
        }
        var sh = xwb.getSheet("sheet1")
        if (null == sh) sh = xwb.createSheet("sheet1")
        println("startIndex ======================================== $startIndex")

        // 添加表头
        if (startIndex == 0) {
            val row = sh.createRow(0)
            var k = 0
            for (key in headers.keys) {
                row.createCell(k++).setCellValue(headers.get(key))
            }
        }

        // 保存内容
        for (j in 0.rangeTo(dataArrays.size - 1)) {
            val row = sh.createRow(startIndex + 1 + j)
            val d = dataArrays[j]
            var k = 0;
            for (key in headers.keys) {
                row.createCell(k++).setCellValue(d.get(key).toString())
            }
        }
        xwb.write(f.outputStream())
        xwb.close()
    }

    println(filePath)
    println("文件生成完成，耗时：$expose_time ms")
}