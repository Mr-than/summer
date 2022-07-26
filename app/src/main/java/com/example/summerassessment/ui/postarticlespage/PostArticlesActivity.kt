package com.example.summerassessment.ui.postarticlespage


import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.example.summerassessment.R
import com.example.summerassessment.base.BaseActivity
import com.example.summerassessment.databinding.ActivityPostArticlesBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.util.*
import kotlin.random.Random

/**
 *   description:发文章页
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */

class PostArticlesActivity : BaseActivity() {

    private lateinit var binding:ActivityPostArticlesBinding

    private val viewModel:PostActivityViewModel by lazy { ViewModelProvider(this).get(PostActivityViewModel::class.java) }

    companion object{
        private var uri:Uri? = null
        private lateinit var outputImage:File
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

    }

    private fun choosePhoto(){


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

            AlertDialog.Builder(this).apply {
                setTitle("图片")
                setMessage("选择图片来源")
                setCancelable(true)
                setPositiveButton("相机"){
                        _, _ ->

                    outputImage = File(externalCacheDir, "out.jpg")
                    try {
                        if (outputImage.exists()) {
                            outputImage.delete()
                        }
                        outputImage.createNewFile()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        FileProvider.getUriForFile(this@PostArticlesActivity, "com.example.fileprovider", outputImage)
                    } else {
                        Uri.fromFile(outputImage)
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
                arrayOf(Manifest.permission.CAMERA),2)
        }



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.post_joke_menu,menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
         R.id.post_joke->{

             if(binding.activityPostJokeEditText.text.toString().isNotEmpty()) {
                 if (isPostImg) {
                     val n = Random.nextLong(100000) + 1000
                     val name = "${n}.png"
                     viewModel.getToken(
                         data!!,
                         name,
                         binding.activityPostJokeEditText.text.toString()
                     )
                 } else {
                     viewModel.postJoke(binding.activityPostJokeEditText.text.toString())
                 }
             }else{
                 Toast.makeText(this,"请输入文本",Toast.LENGTH_SHORT).show()
             }

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


    @Deprecated("Deprecated in Java")
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
            else -> {
                if (resultCode == RESULT_OK) {
                    try {
                        binding.activityPostJokeImgViewUncheckImg.visibility=View.VISIBLE
                        val bitmap =
                            BitmapFactory.decodeStream(contentResolver.openInputStream(uri!!))

                       binding.activityPostJokeImgViewCheckedImg.setImageBitmap(rotateIfRequired(bitmap))
                        binding.activityPostJokeImgViewUncheckImg.setOnClickListener {
                            isPostImg=false
                            binding.activityPostJokeImgViewUncheckImg.visibility=View.GONE
                        }

                        val bao = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bao)

                        val d = bao.toByteArray()
                        Companion.data =d

                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }
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

            binding.activityPostJokeImgViewCheckedImg.setImageBitmap(bitmap)
            binding.activityPostJokeImgViewUncheckImg.visibility= View.VISIBLE

            val bao = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bao)

            binding.activityPostJokeImgViewUncheckImg.setOnClickListener {
                isPostImg=false
                binding.activityPostJokeImgViewUncheckImg.visibility=View.GONE
            }

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

    private fun rotateIfRequired(bitmap: Bitmap):Bitmap{
        val exif=ExifInterface(outputImage.path)
        return when(exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL)){
            ExifInterface.ORIENTATION_ROTATE_90->rotateBitmap(bitmap,90)
            ExifInterface.ORIENTATION_ROTATE_180->rotateBitmap(bitmap,180)
            ExifInterface.ORIENTATION_ROTATE_270->rotateBitmap(bitmap,270)
            else->bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap,degree:Int):Bitmap{
        val matrix=Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)
        bitmap.recycle()
        return rotatedBitmap
    }

}