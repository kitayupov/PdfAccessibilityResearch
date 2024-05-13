package com.example.pdfium

import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shockwave.pdfium.PdfiumCore
import java.io.File

class PdfiumActivity : AppCompatActivity() {

    private val path: String by lazy { intent.getStringExtra("fileName") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfium)

        parsePage()
    }

    private fun parsePage() {
        val fd = ParcelFileDescriptor.open(File(path), ParcelFileDescriptor.MODE_READ_ONLY)
        val core = PdfiumCore(this)
        val document = core.newDocument(fd)

        val sb = StringBuilder()
        findViewById<TextView>(R.id.textView).text = sb.toString()
    }
}
