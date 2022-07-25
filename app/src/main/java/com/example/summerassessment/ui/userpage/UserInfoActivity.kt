package com.example.summerassessment.ui.userpage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.summerassessment.base.BaseActivity
import com.example.summerassessment.databinding.ActivityUserInfoBinding
import com.example.summerassessment.ui.adapter.userinfoadapter.CurrentUserAdapter
import com.example.summerassessment.ui.adapter.userinfoadapter.UserInfoParentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class UserInfoActivity : BaseActivity() {

    private lateinit var binding:ActivityUserInfoBinding
    private val viewModel by lazy { ViewModelProvider(this).get(UserInfoViewModel::class.java) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        this.setSupportActionBar(binding.activityUserPageToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setDisplayShowTitleEnabled(false)

        val ex=intent.getStringExtra("user_id")
        val page=intent.getStringExtra("page")
        val current=intent.getStringExtra("type")


        val pages=page?.split("|")

        if(current !=null){
            binding.activityUserPageVp2ParentVp2.adapter=CurrentUserAdapter(this,ex?.toInt()?:0)
            TabLayoutMediator(binding.activityUserPageTabLayoutParentVp2, binding.activityUserPageVp2ParentVp2) { tab, p ->
                when (p) {
                    0 -> tab.text = "作品"
                    1 -> tab.text = "喜欢"
                    2->tab.text="评论"
                    3->tab.text="收藏"
                }
            }.attach()
        }else {
            binding.activityUserPageVp2ParentVp2.adapter =
                UserInfoParentAdapter(this, ex?.toInt() ?: 0)

            TabLayoutMediator(binding.activityUserPageTabLayoutParentVp2, binding.activityUserPageVp2ParentVp2) { tab, p ->
                when (p) {
                    0 -> tab.text = "作品"
                    1 -> tab.text = "喜欢"
                }
            }.attach()
        }



        viewModel.userMessageLiveData.observe(this){
            binding.activityTextViewUserFans.text="${it.fansNum} 粉丝数"
            binding.activityTextViewUserInTime.text="入驻段子乐: ${it.joinTime}"
            binding.activityTextViewUserMotto.text=it.sigbature
            binding.activityTextViewUserNickName.text=it.nickname
            binding.activityTextViewUserLiked.text="${it.likeNum} 获赞"
            binding.activityButtonUserAttend.text="${it.attentionNum} 关注"

            Glide.with(this).load(it.avatar).into(binding.activityImageViewUserPortraitSmall)
            Glide.with(this).load(it.cover).into(binding.activityImageViewUserPortraitBig)
        }

        viewModel.getUserMessage(ex?.toInt()?:0)

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