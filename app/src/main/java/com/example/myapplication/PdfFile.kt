package com.example.myapplication

/**
 * @author k.i.tayupov
 */

class PdfFile(private var fileName: String, private var filePath: String) {

    fun getFileName(): String {
        return fileName
    }

    fun setFileName(fileName: String) {
        this.fileName = fileName
    }

    fun getFilePath(): String {
        return filePath
    }

    fun setFilePath(filePath: String) {
        this.filePath = filePath
    }
}
