package com.example.summerassessment.ui.userpage

import android.os.Bundle
import android.view.MenuItem
import com.example.summerassessment.base.BaseActivity
import com.example.summerassessment.databinding.ActivityUserInfoBinding
import com.example.summerassessment.ui.adapter.userinfoadapter.UserInfoParentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class UserInfoActivity : BaseActivity() {

    private lateinit var binding:ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        this.setSupportActionBar(binding.activityUserPageToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setDisplayShowTitleEnabled(false)

        val ex=intent.getStringExtra("user_id")


        binding.activityUserPageVp2ParentVp2.adapter=UserInfoParentAdapter(this,ex?.toInt()?:0)


        TabLayoutMediator(binding.activityUserPageTabLayoutParentVp2, binding.activityUserPageVp2ParentVp2) { tab, p ->
            when (p) {
                0 -> tab.text = "作品"
                1 -> tab.text = "喜欢"
            }
        }.attach()





    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return true
    }
}