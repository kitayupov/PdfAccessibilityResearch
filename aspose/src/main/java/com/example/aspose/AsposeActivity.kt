package com.example.aspose

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aspose.pdf.Document
import com.aspose.pdf.TextAbsorber

class AsposeActivity : AppCompatActivity() {

    private val path: String by lazy { intent.getStringExtra("fileName") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aspose)

        parsePage()
    }

    private fun parsePage() {
        val document = Document(path)

        val textAbsorber = TextAbsorber()
        document.pages.accept(textAbsorber)
        val page = document.pages.get_Item(1)
        textAbsorber.visit(page)

        val extractedText = textAbsorber.text
        findViewById<TextView>(R.id.textView).text = extractedText
    }
}
