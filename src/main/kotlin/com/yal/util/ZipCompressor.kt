package com.enlink.es.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.BufferedInputStream
import java.io.File
import java.util.zip.CRC32
import java.util.zip.CheckedOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * 功能描述: Zip工具类
 */
class ZipCompressor(val zipPathName: String) {
    private val LOGGER: Logger = LoggerFactory.getLogger(ZipCompressor::class.java)
    private val BUFFER_SIZE = 8192
    private val zipFile: File

    init {
        zipFile = File(zipPathName)
    }

    /**
     * 功能描述: 将多个文件压缩进压缩包zipFile
     */
    fun compress(vararg filePaths: String) {
        if (!zipFile.exists()) {
            zipFile.parentFile.mkdirs()
            zipFile.createNewFile()
        }
        val zipOut = ZipOutputStream(CheckedOutputStream(zipFile.outputStream(), CRC32()))
        filePaths.forEach { it ->
            val f = File(it)
            LOGGER.info("Path==>" + f.path)
            compressFile(f, zipOut, "")
        }
        zipOut.close()
    }

    fun compressFile(f: File, zipOut: ZipOutputStream, baseDir: String) {
        if (f.exists()) {
            zipOut.putNextEntry(ZipEntry(baseDir + f.name))
            val buff = BufferedInputStream(f.inputStream())
            val data = ByteArray(BUFFER_SIZE)
            var isWrite = true
            while (isWrite) {
                val count = buff.read(data, 0, BUFFER_SIZE)
                if (count > 0) {
                    zipOut.write(data, 0, count)
                    continue
                }
                isWrite = false
            }
            buff.close()
        }
    }
}