package com.example.summerassessment.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class APP:Application() {
    override fun onCreate(){
        super.onCreate()

        context=applicationContext
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context
        fun getApp()=context

        const val BASE_URL:String="http://tools.cretinzp.com/jokes/"
        const val PROJECT_TOKEN:String="6CF1090AB4834C63A5D5AAC91343F858"
        const val PASSWORD:String="cretinzp**273846"

    }

}