package com.example.summerassessment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.summerassessment.R
import com.example.summerassessment.base.APP
import com.example.summerassessment.ui.logactivity.LoginActivity
import com.example.summerassessment.ui.mainactivity.MainActivity
import kotlin.concurrent.thread

/**
 *   description:最开始打开的activity
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val a=getSharedPreferences("token", MODE_PRIVATE)
        val token:String=a.getString("t","")!!
        if(token.isNotEmpty()&&token!=""){
            startActivity(Intent(this,MainActivity::class.java))
            APP.token=token
            APP.isLogin=true
        }else{
            startActivity(Intent(this,MainActivity::class.java))
            startActivity(Intent(this,LoginActivity::class.java))
        }


        thread {
            Thread.sleep(500)
            finish()
        }

    }
}