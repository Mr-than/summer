package com.example.summerassessment.ui.adapter.userinfoadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.summerassessment.databinding.Vp2VideoRvItemBinding
import com.example.summerassessment.model.Data
import com.example.summerassessment.model.DataU
import com.example.summerassessment.util.decrypt

class UserPageVideoAdapter(private val context: Context) : ListAdapter<DataU, UserPageVideoAdapter.ViewHolder>(CALL_BACK) {

    companion object {

        val CALL_BACK = object : DiffUtil.ItemCallback<DataU>() {
            override fun areItemsTheSame(oldItem: DataU, newItem: DataU): Boolean {
                return oldItem.jokeId == newItem.jokeId
            }

            override fun areContentsTheSame(oldItem: DataU, newItem: DataU): Boolean {
                return oldItem == newItem
            }
        }

    }

    class ViewHolder(val binding: Vp2VideoRvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            Vp2VideoRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(getItem(position).cover.decrypt()).into(holder.binding.vp2VideoRvItemImgView)
        holder.binding.vp2VideoRvItemLikeNum.text=getItem(position).likeNum
    }

}