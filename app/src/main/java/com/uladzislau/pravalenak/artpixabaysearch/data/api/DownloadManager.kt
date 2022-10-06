package com.uladzislau.pravalenak.artpixabaysearch.data.api

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadManager(
    private val context: Context
) {

    fun saveToInternalStorage(bitmapImage: Bitmap, name: String): String? {
        val cw = ContextWrapper(context)
        // path to /data/data/yourapp/app_data/imageDir
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        // Create imageDir
        val mypath = File(directory, name)
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return directory.absolutePath
    }

    fun mLoad(string: String): Bitmap? {
        val url = URL(string)
        val connection: HttpURLConnection
        try {
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream: InputStream = connection.inputStream
            val bufferedInputStream = BufferedInputStream(inputStream)

            val bitmap = BitmapFactory.decodeStream(bufferedInputStream)
            connection.disconnect()

            return bitmap
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun getFromInternalStorage(string: String): Bitmap? {
        val cw = ContextWrapper(context)
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)

        val myPath = File(directory, string)
        if (!myPath.exists()) return null

        val inputStream = FileInputStream(myPath)
        val bitmap = BitmapFactory.decodeStream(inputStream)

        inputStream.close()

        return bitmap
    }
}