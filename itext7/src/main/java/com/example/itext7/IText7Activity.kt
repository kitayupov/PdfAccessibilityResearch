package com.example.itext7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor

/**
 * @author k.i.tayupov
 */
class IText7Activity : AppCompatActivity() {

    private val path: String? by lazy { intent.getStringExtra("fileName") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itext7)

        parsePage()
    }

    private fun parsePage() {
        val reader = PdfReader(path)
        val document = PdfDocument(reader)
        val sb = StringBuilder()
        for (page in 1..document.numberOfPages) {
            sb.append(PdfTextExtractor.getTextFromPage(document.getPage(page)))
        }
        findViewById<TextView>(R.id.textView).text = sb.toString()
    }
}
