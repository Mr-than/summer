package com.example.summerassessment.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.summerassessment.databinding.ActivityMainBinding

import com.example.summerassessment.ui.homefragment.HomeFragment
import com.example.summerassessment.ui.adapter.MainActivityVpAdapter

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val viewModel:MainActivityViewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainActivityVpPages.adapter=MainActivityVpAdapter(this){
            when(it){
                0->{
                    HomeFragment()
                }
                1->{
                    HomeFragment()
                }
                2->{
                    HomeFragment()
                }
                else->{
                    HomeFragment()
                }
            }
        }
        binding.mainActivityVpPages.isUserInputEnabled=false

        viewModel.switchPageLiveData.observe(this){
            binding.mainActivityVpPages.currentItem=it
        }

    }
}