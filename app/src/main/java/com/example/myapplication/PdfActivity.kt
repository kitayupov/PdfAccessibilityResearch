package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.DisplayMetrics
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apryse.ApryseActivity
import com.example.aspose.AsposeActivity
import com.example.itext5.IText5Activity
import com.example.itext7.IText7Activity
import com.example.mu_pdf.MuPdfActivity
import java.io.File

class PdfActivity : AppCompatActivity(), OnClickListener {

    private val path: String by lazy { intent.getStringExtra(FILE_NAME) ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)
    }

    private var pdfRenderer: PdfRenderer? = null
    private var curPage: PdfRenderer.Page? = null
    private var descriptor: ParcelFileDescriptor? = null
    private var currentZoomLevel = 5f

    override fun onStart() {
        super.onStart()
        try {
            openPdfRenderer()
            displayPage()
        } catch (e: Exception) {
            Toast.makeText(this, "PDF-файл защищен паролем.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openPdfRenderer() {
        val file = File(path)
        try {
            descriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            pdfRenderer = PdfRenderer(requireNotNull(descriptor) { "descriptor is null" })
        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayPage() {
        // закрываем текущую страницу
        if (curPage != null) requireNotNull(curPage) { "curPage is null" }.close()
        // открываем нужную страницу
        curPage = requireNotNull(pdfRenderer) { "pdfRenderer is null" }.openPage(0)

        // определяем размеры Bitmap
        val newWidth = (resources.displayMetrics.widthPixels * requireNotNull(curPage) { "curPage is null" }.width / 72 * currentZoomLevel / 40).toInt()
        val newHeight = (resources.displayMetrics.heightPixels * requireNotNull(curPage) { "curPage is null" }.height / 72 * currentZoomLevel / 64).toInt()
        val bitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888)

        val matrix = Matrix()
        val dpiAdjustedZoomLevel = (currentZoomLevel * DisplayMetrics.DENSITY_MEDIUM / resources.displayMetrics.densityDpi)
        matrix.setScale(dpiAdjustedZoomLevel, dpiAdjustedZoomLevel)

        requireNotNull(curPage) { "curPage is null" }.render(bitmap, null, matrix, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

        // отображаем результат рендера
        findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.iText5 -> launchActivity(IText5Activity::class.java)
            R.id.iText7 -> launchActivity(IText7Activity::class.java)
            R.id.Aspose -> launchActivity(AsposeActivity::class.java)
            R.id.Apryse -> launchActivity(ApryseActivity::class.java)
            R.id.MuPdf -> launchActivity(MuPdfActivity::class.java)
            R.id.Preview -> ApryseActivity.openExternalViewer(this, path)
        }
    }

    private fun launchActivity(clazz: Class<*>) {
        val intent = Intent(this@PdfActivity, clazz)
            .putExtra(FILE_NAME, path)
        startActivity(intent)
    }

    companion object {
        const val FILE_NAME = "fileName"
    }
}
