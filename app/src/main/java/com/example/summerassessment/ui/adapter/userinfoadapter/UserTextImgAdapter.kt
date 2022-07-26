package com.example.summerassessment.ui.adapter.userinfoadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.summerassessment.databinding.Vp2TextIamgeRvItemBinding
import com.example.summerassessment.model.Data
import com.example.summerassessment.util.decrypt


/**
 *   description:用户界面的图片、文字adapter
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
class UserTextImgAdapter(private val context: Context) : ListAdapter<Data, UserTextImgAdapter.ViewHolder>(CALL_BACK) {

    companion object {
        val CALL_BACK = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.joke.jokesId == newItem.joke.jokesId
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(val binding: Vp2TextIamgeRvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            Vp2TextIamgeRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.vp2TextImgRvItemText.text=getItem(position).joke.content

        if(getItem(position).joke.imageUrl.isNotEmpty()){
            holder.binding.vp2TextImgRvItemImg.visibility=View.VISIBLE
            Glide.with(context).load(getItem(position).joke.imageUrl.decrypt()).into(holder.binding.vp2TextImgRvItemImg)
        }else{
            holder.binding.vp2TextImgRvItemImg.visibility=View.GONE
        }

        holder.binding.vp2TextImgRvItemCommentText.text=getItem(position).info.commentNum.toString()
        holder.binding.vp2TextImgRvItemLikeText.text=getItem(position).info.likeNum.toString()
        holder.binding.vp2TextImgRvItemShareText.text=getItem(position).info.shareNum.toString()
        holder.binding.vp2TextImgRvItemHateText.text=getItem(position).info.disLikeNum.toString()

    }


}