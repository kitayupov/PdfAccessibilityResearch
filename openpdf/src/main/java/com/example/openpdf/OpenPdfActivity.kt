package com.example.openpdf

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lowagie.text.pdf.PdfReader
import com.lowagie.text.pdf.parser.PdfTextExtractor

class OpenPdfActivity : AppCompatActivity() {

    private val path: String by lazy { intent.getStringExtra("fileName") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_pdf)

        parsePage()
    }

    private fun parsePage() {
        val reader = PdfReader(path)
        val txt = PdfTextExtractor(reader)
        val page = txt.getTextFromPage(1)
        findViewById<TextView>(R.id.textView).text = page
    }
}
