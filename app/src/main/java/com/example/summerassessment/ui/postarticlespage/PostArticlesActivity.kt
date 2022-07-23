package com.example.summerassessment.ui.postarticlespage


import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.summerassessment.R
import com.example.summerassessment.base.BaseActivity
import com.example.summerassessment.databinding.ActivityPostArticlesBinding
import com.qiniu.android.common.FixedZone
import com.qiniu.android.storage.Configuration
import com.qiniu.android.storage.UploadManager
import java.io.ByteArrayOutputStream
import java.io.File


class PostArticlesActivity : BaseActivity() {

    private lateinit var binding:ActivityPostArticlesBinding


    companion object{
        private var uri:Uri? = null
        private const val CHOOSE_PHOTO = 2
        private const val TAKE = 1
        private var isPostImg=false
        private var data:ByteArray?=null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPostArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.activityPostJokeToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setDisplayShowTitleEnabled(false)


        binding.activityPostJokePostImg.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            } else {
                choosePhoto()
            }
        }


        binding.activityPostJokeImgViewUncheckImg.visibility=View.GONE
        binding.activityPostJokeImgViewUncheckImg.setOnClickListener {
            isPostImg=false
            binding.activityPostJokeImgViewUncheckImg.visibility=View.GONE
        }



    }

    private fun choosePhoto(){



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

            AlertDialog.Builder(this).apply {
                setTitle("图片")
                setMessage("选择图片来源")
                setCancelable(true)
                setPositiveButton("相机"){
                        _, _ ->

                    val file = File(externalCacheDir, "out.jpg")
                    try {
                        if (file.exists()) {
                            file.delete()
                        }
                        file.createNewFile()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        FileProvider.getUriForFile(this@PostArticlesActivity, "com.example.fileprovider", file)
                    } else {
                        Uri.fromFile(file)
                    }

                    val intent = Intent("android.media.action.IMAGE_CAPTURE")
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                    startActivityForResult(intent, TAKE)


                }
                setNegativeButton("图库"){
                        _, _ ->
                    open()
                }
            }.show()

        }else{
            //否则去请求相机权限
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),2);
        }



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.post_joke_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
         R.id.post_joke->{

         }
         R.id.home_search->{
             finish()
            }
        }
        return true
    }


    private fun open() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, CHOOSE_PHOTO)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CHOOSE_PHOTO -> {
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handle1(data)
                    } else {
                        handle2(data)
                    }
                }
            }
            else -> {}
        }
    }

    private fun handle1(data: Intent?) {
        var imagePath: String? = null
        val uri = data!!.data
        if (DocumentsContract.isDocumentUri(this, uri)) {
            val docId = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents" == uri!!.authority) {
                val id = docId.split(":").toTypedArray()[1]
                val selection = MediaStore.Images.Media._ID + "=" + id
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection)
            } else if ("com.android.providers.downloads.documents" == uri.authority) {
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    java.lang.Long.valueOf(docId)
                )
                imagePath = getImagePath(contentUri, null)
            }
        } else if ("content".equals(uri!!.scheme, ignoreCase = true)) {
            imagePath = getImagePath(uri, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            imagePath = uri.path
        }
        displayImage(imagePath)
    }

    @SuppressLint("Range")
    private fun getImagePath(uri: Uri?, selection: String?): String? {
        var path: String? = null
        val cursor = contentResolver.query(uri!!, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path
    }


    private fun handle2(data: Intent?) {
        val uri = data!!.data
        val imagePath = getImagePath(uri, null)
        displayImage(imagePath)
    }


    private fun displayImage(imagePath: String?) {
        if (imagePath != null) {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            val bao = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao)


            binding.activityPostJokeImgViewCheckedImg.setImageBitmap(bitmap)
            binding.activityPostJokeImgViewUncheckImg.visibility= View.VISIBLE
            val d = bao.toByteArray()
            //val a = imagePath.split("/").toTypedArray()
            //postTo(data, a[a.size - 1])
            data=d
            isPostImg=true
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    choosePhoto()
                }else{
                    Toast.makeText(this,"你禁止了存储权限",Toast.LENGTH_SHORT).show()
                }

            }
            2->{
                if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    choosePhoto()
                }else{
                    Toast.makeText(this,"你禁止了相机权限",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}