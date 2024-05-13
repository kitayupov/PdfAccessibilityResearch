package com.example.itext5

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.ImageRenderInfo
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import com.itextpdf.text.pdf.parser.TextExtractionStrategy
import com.itextpdf.text.pdf.parser.TextRenderInfo

class IText5Activity : AppCompatActivity() {

    private val path: String? by lazy { intent.getStringExtra("fileName") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itext5)

        parsePage()
    }

    private fun parsePage() {
        val reader = PdfReader(path)

        val group = findViewById<ViewGroup>(R.id.contentView)

        for (page in 1..reader.numberOfPages) {
            PdfTextExtractor.getTextFromPage(reader, page, object : TextExtractionStrategy {
                private var stringBuilder = StringBuilder()

                override fun beginTextBlock() {
                    stringBuilder.clear()
                }

                override fun renderText(renderInfo: TextRenderInfo?) {
                    stringBuilder.append(renderInfo?.text)
                }

                override fun endTextBlock() {
                    val string = stringBuilder.toString()
                    if (string.isNotBlank()) {
                        val textView = TextView(this@IText5Activity).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            setPadding(8, 8, 8, 8)
                            text = string
                        }
                        group.addView(textView)
                    }
                }

                override fun renderImage(renderInfo: ImageRenderInfo?) = Unit

                override fun getResultantText(): String = String()
            })
        }
    }
}
