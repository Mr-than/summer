package com.example.summerassessment.ui.follow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.summerassessment.databinding.ActivityFollowsFansBinding
import com.example.summerassessment.ui.adapter.follow.FollowFanAdapter
import com.example.summerassessment.ui.adapter.follow.FollowFanRvAdapter
import com.example.summerassessment.ui.follow.viewmodel.FollowsFansViewModel
import com.google.android.material.tabs.TabLayoutMediator


/**
 *   description:关注粉丝页
 *   @author:冉跃
 *   email:2058109198@qq.com
 *
 */
class FollowsFansActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFollowsFansBinding
    private val viewModel: FollowsFansViewModel by lazy { ViewModelProvider(this).get(
        FollowsFansViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFollowsFansBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.setSupportActionBar(binding.activityFollowFanToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setDisplayShowTitleEnabled(false)

        val ex=intent.getStringExtra("user_id")
        val name=intent.getStringExtra("nike_name")

        binding.activityFollowFanTvCurrentUserNikeName.text=name

        val adapter1=FollowFanRvAdapter(this,1)
        val adapter2=FollowFanRvAdapter(this,2)

        val list:ArrayList<FollowFanRvAdapter> = ArrayList()
        list.run {
            add(adapter1)
            add(adapter2)
        }
        binding.activityFollowFanVp2.adapter=FollowFanAdapter(this,list)
        TabLayoutMediator(binding.activityFollowFanTabLayout, binding.activityFollowFanVp2) { tab, p ->
            when (p) {
                0 -> tab.text = "关注"
                1 -> tab.text = "粉丝"

            }
        }.attach()

        viewModel.followList.observe(this){
            adapter1.submitList(it)

        }
        viewModel.fanList.observe(this){
            adapter2.submitList(it)
        }

        viewModel.getFollowData(ex?:"0")
        viewModel.getFanData(ex?:"0")
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