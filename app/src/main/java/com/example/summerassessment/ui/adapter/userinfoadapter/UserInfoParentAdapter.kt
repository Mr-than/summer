package com.example.summerassessment.ui.adapter.userinfoadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.databinding.Vp2ActivityUserInfoItemLayoutBinding
import com.example.summerassessment.ui.userpage.UserInfoViewModel
import com.google.android.material.tabs.TabLayoutMediator


/**
 *   description:用户界面的最外层vp2
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
open class UserInfoParentAdapter(
    private val context: Context,
    private val id: Int,
    private val p: Int = 0
): RecyclerView.Adapter<UserInfoParentAdapter.ViewHolder>() {

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ParentViewHolder(val binding: Vp2ActivityUserInfoItemLayoutBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ParentViewHolder(
            Vp2ActivityUserInfoItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is ParentViewHolder) {
            holder.binding.vp2ActivityUserInfoItemLayoutVp2Child.adapter =
                UserInfoChildAdapter(context, id, position)
            holder.binding.vp2ActivityUserInfoItemLayoutVp2Child.tag = position.toString()
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
    }


    override fun getItemCount(): Int {
        return 2
    }


}