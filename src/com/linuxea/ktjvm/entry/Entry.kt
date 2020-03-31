package com.linuxea.ktjvm.entry

import java.io.File
import java.io.FileInputStream
import java.util.zip.ZipFile

abstract class Entry {

    /**
     * className 为文件名称 + .class，例如 java/lang/object.class
     */
    abstract fun find(className: String): ByteArray?

}

/**
 * 目录 entry
 */
class DirEntry(private val absDir: String) : Entry() {

    override fun find(className: String): ByteArray? {
        val file = File(absDir + File.separator + className)
        return FileInputStream(file).readBytes()
    }

}

/**
 * 压缩包 entry
 */
class ZipEntry(private val absDir: String) : Entry() {

    override fun find(className: String): ByteArray? {
        val file = File(absDir)
        val zipFile = ZipFile(file)
        zipFile.getEntry(className)?.let {
            return zipFile.getInputStream(it).readBytes()
        }
        return null
    }

}





