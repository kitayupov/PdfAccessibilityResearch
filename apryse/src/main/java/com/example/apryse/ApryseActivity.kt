package com.example.apryse

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.pdftron.pdf.PDFDoc
import com.pdftron.pdf.TextExtractor
import com.pdftron.pdf.controls.DocumentActivity

class ApryseActivity : AppCompatActivity() {

    private val path: String by lazy { intent.getStringExtra("fileName") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apryse)

        parsePage()
    }

    @SuppressLint("SetTextI18n")
    private fun parsePage() {
        val doc = PDFDoc(path)
        val txt = TextExtractor()
        txt.begin(doc.getPage(1))

        var word: TextExtractor.Word
        var line: TextExtractor.Line = txt.firstLine
        while (line.isValid) {
            word = line.firstWord
            while (word.isValid) {
                word.string
                word = word.nextWord
            }
            line = line.nextLine
        }

        val text = txt.asText
        System.err.println(text)

        val xml = txt.asXML
        System.err.println(xml)

        findViewById<TextView>(R.id.textView).text = "TXT\n\n$text\n\n\nXML\n\n$xml"
    }

    companion object {
        fun openExternalViewer(context: Context, path: String) {
            val intent = DocumentActivity.IntentBuilder.fromActivityClass(context, DocumentActivity::class.java)
                .withUri(path.toUri())
                .build()
            context.startActivity(intent)
        }
    }
}
