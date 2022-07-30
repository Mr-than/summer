package com.example.summerassessment.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.summerassessment.base.APP
import com.example.summerassessment.databinding.ActivityLoginBinding
import com.example.summerassessment.ui.login.viewmodel.LoginViewModel
import com.example.summerassessment.ui.main.MainActivity


/**
 *   description:登录页
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private val viewModel: LoginViewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginCode!!.setOnClickListener {

            if(binding.username.text.toString().isNotEmpty()){
                if(binding.username.text.toString().length<0||binding.username.text.toString().length>11){
                    Toast.makeText(this,"请输入格式正确的手机号",Toast.LENGTH_SHORT).show()
                }else
                viewModel.getCode(binding.username.text.toString())
            }else{
                Toast.makeText(this,"请输入手机号",Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.codeLiveData.observe(this){
            if(it=="手机号格式不正确"){
                Toast.makeText(this,"请输入格式正确的手机号",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"获取验证码成功",Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginLiveData.observe(this){
            val sp=getSharedPreferences("token", MODE_PRIVATE).edit()
            sp.putString("t",it.token)
            sp.apply()
            APP.token=it.token
            APP.isLogin=true
            startActivity(Intent(this, MainActivity::class.java))

        }

        binding.login.setOnClickListener {
            if(binding.username.text.toString().length<0||binding.username.text.toString().length>11){
                Toast.makeText(this,"请输入格式正确的手机号",Toast.LENGTH_SHORT).show()
            }else{
                if(binding.password.text.toString().isEmpty()){
                    Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show()
                }else{
                 viewModel.logIn(binding.username.text.toString(),binding.password.text.toString())
                }
            }

        }


    }
}