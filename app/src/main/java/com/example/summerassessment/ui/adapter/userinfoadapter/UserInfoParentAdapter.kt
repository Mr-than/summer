package com.example.summerassessment.ui.adapter.userinfoadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.databinding.Vp2ActivityUserInfoItemLayoutBinding
import com.google.android.material.tabs.TabLayoutMediator

class UserInfoParentAdapter(private val context: Context,private val id:Int) : RecyclerView.Adapter<UserInfoParentAdapter.ViewHolder>() {

    class ViewHolder(val binding: Vp2ActivityUserInfoItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            Vp2ActivityUserInfoItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.vp2ActivityUserInfoItemLayoutVp2Child.adapter = UserInfoChildAdapter(context,id,position)
        holder.binding.vp2ActivityUserInfoItemLayoutVp2Child.tag=position.toString()
        TabLayoutMediator(
            holder.binding.vp2ActivityUserInfoItemLayoutTabLayoutChild,
            holder.binding.vp2ActivityUserInfoItemLayoutVp2Child
        ) { tab, p ->
            when (p) {
                0 -> tab.text = "文字·图片"
                1 -> tab.text = "视频"
            }
        }.attach()
    }


    override fun getItemCount(): Int {
        return 2
    }


}