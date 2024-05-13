package com.example.mu_pdf

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artifex.mupdf.viewer.DocumentActivity
import java.io.File

class MuPdfActivity : AppCompatActivity() {

    private val path: String by lazy { intent.getStringExtra("fileName") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, DocumentActivity::class.java)
        intent.setAction(Intent.ACTION_VIEW)
        intent.setData(Uri.fromFile(File(path)))
        startActivity(intent)
    }
}
