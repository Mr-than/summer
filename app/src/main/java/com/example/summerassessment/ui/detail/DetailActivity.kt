package com.example.summerassessment.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.summerassessment.R
import com.example.summerassessment.util.ZoomImageView


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val ex = intent.getStringExtra("url")

        val img:ZoomImageView=findViewById(R.id.big_photo)

        Glide.with(this).load(ex).into(img)


    }
}