package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val FILE_REQUEST = 42

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val list = ArrayList<PdfFile>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getFilesList()
        updateListView()
    }

    private fun getFilesList() {
        try {
            filesDir.listFiles { _, name -> name.endsWith(".pdf") }
                ?.forEach { file ->
                    list.add(PdfFile(file.name, file.absolutePath))
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun updateListView() {
        listView = findViewById(R.id.listView)
        listView.adapter = adapter
        listView.onItemClickListener = OnItemClickListener { _, _, index, _ ->
            val intent = Intent(this@MainActivity, PdfActivity::class.java).apply {
                putExtra(PdfActivity.FILE_NAME, list[index].getFilePath())
            }
            startActivity(intent)
        }
    }

    private val adapter = object : BaseAdapter() {
        override fun getCount(): Int = list.size
        override fun getItem(position: Int): PdfFile = list[position]
        override fun getItemId(position: Int): Long = position.toLong()

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = layoutInflater.inflate(R.layout.list_item, parent, false).apply {
                findViewById<TextView>(R.id.txtFileName).apply {
                    text = getItem(position).getFileName()
                }
            }
            return view
        }
    }
}
