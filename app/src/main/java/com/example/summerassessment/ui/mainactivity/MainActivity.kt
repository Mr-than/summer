package com.example.summerassessment.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.summerassessment.databinding.ActivityMainBinding

import com.example.summerassessment.ui.homefragment.HomeFragment
import com.example.summerassessment.ui.adapter.MainActivityVpAdapter

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainActivityVpPages.adapter=MainActivityVpAdapter(this){
            p->
            HomeFragment()
        }

        binding.mainActivityVpPages.isUserInputEnabled=false




    }
}