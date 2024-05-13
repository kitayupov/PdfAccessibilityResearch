package com.example.ironpdf

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IronPdfActivity : AppCompatActivity() {

    private val path: String by lazy { intent.getStringExtra("fileName") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iron_pdf)

        parsePage()
    }

    private fun parsePage() {
//        PdfDocument.fromFile(Path.of() .of(path.toUri()))
//        val reader = PdfReader(path)
//        val document = PdfDocument(reader)
        val sb = StringBuilder()
//        for (page in 1..document.numberOfPages) {
//            sb.append(PdfTextExtractor.getTextFromPage(document.getPage(page)))
//        }
        findViewById<TextView>(R.id.textView).text = sb.toString()
    }
}
