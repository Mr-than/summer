package com.example.summerassessment.ui.mainactivity

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.example.summerassessment.R
import com.example.summerassessment.base.BaseActivity
import com.example.summerassessment.databinding.ActivityMainBinding

import com.example.summerassessment.ui.homefragment.HomeFragment
import com.example.summerassessment.ui.adapter.MainActivityVpAdapter
import com.example.summerassessment.ui.brushvideofragment.BrushVideoFragment

class MainActivity : BaseActivity(){

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
                    BrushVideoFragment()
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

            if(it==1) {
                binding.bottom.getChildAt(0).background =
                    ResourcesCompat.getDrawable(resources, R.drawable.shape_shadow_black, null)
            }else{
                binding.bottom.getChildAt(0).background =
                    ResourcesCompat.getDrawable(resources, R.drawable.shape_shadow, null)
            }
            binding.mainActivityVpPages.currentItem=it
        }

    }
}