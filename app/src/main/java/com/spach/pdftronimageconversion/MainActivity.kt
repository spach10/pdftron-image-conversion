package com.spach.pdftronimageconversion

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pdftron.pdf.Convert
import com.pdftron.pdf.PDFDoc
import com.spach.pdftronimageconversion.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var pdfDoc: PDFDoc? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertImage()
    }

    private fun insertImage() {
        val file = getFileFromAssets()

        // Using ImageView
//        binding.ivImage.visibility = View.VISIBLE
//        Glide.with(this)
//            .load(file)
//            .listener(object : RequestListener<Drawable> {
//                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                    return false
//                }
//
//                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                    return false
//                }
//
//            })
//            .into(binding.ivImage)

        // Using PDFTRON
        binding.pdfViewCtrl.visibility = View.VISIBLE
        pdfDoc = PDFDoc()
        Convert.toPdf(pdfDoc, file.absolutePath)
        binding.pdfViewCtrl.doc = pdfDoc
    }

    private fun getFileFromAssets(): File {
        val fileName = "test.png"
        return File(this.cacheDir, fileName).also {
            if (!it.exists()) {
                it.outputStream().use { cache ->
                    this.assets.open(fileName).use { inputStream ->
                        inputStream.copyTo(cache)
                    }
                }
            }
        }
    }
}