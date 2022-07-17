package com.example.summerassessment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.summerassessment.R
import com.example.summerassessment.databinding.HomeRecommendVpItemRvItemLayoutBinding
import com.example.summerassessment.model.Data
import com.example.summerassessment.util.decrypt

class HomeRecommendAdapter(private val context: Context) : PagingDataAdapter<Data,HomeRecommendAdapter.ViewHolder>(COMPARATOR){

    companion object {
        private val list= mutableListOf<String>()
        private val COMPARATOR= object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.joke.jokesId == newItem.joke.jokesId
            }
            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return false
            }
            override fun getChangePayload(oldItem: Data, newItem: Data)= listOf<String>()
        }
    }


    class ViewHolder(val binding:HomeRecommendVpItemRvItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.homePageVpItemRvTextLikeImage.setOnClickListener{
                val position=layoutPosition
                if(!(binding.homePageVpItemRvTextLikeImage.tag as Boolean)){
                    binding.homePageVpItemRvTextLikeImage.setImageResource(R.drawable.like_click)
                    list.add(position.toString())
                    binding.homePageVpItemRvTextLikeImage.tag=true
                }else{
                    binding.homePageVpItemRvTextLikeImage.setImageResource(R.drawable.like)
                    if(list.contains(position.toString())){
                        list.remove(position.toString())
                    }
                    binding.homePageVpItemRvTextLikeImage.tag=false
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.homePageVpItemRvTextNikeName.text=getItem(position)?.user?.nickName

        holder.binding.homePageVpItemRvTextPhoto.visibility = GONE
        getItem(position)?.joke?.imageUrl?.run {
            if(this!="") {
                val url=decrypt()
                if(url!=""){
                    holder.binding.homePageVpItemRvTextPhoto.visibility = VISIBLE
                    Glide.with(context).load(url)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                        .into(holder.binding.homePageVpItemRvTextPhoto)
                }
            }
        }

        getItem(position)?.user?.avatar.run {
            if(this!="") {
                Glide.with(context).load(this)
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .into(holder.binding.homePageVpItemRvTextHead)
            }
        }

        holder.binding.homePageVpItemRvTextLikeImage.tag = false

        if(list.isNotEmpty()){
            holder.binding.homePageVpItemRvTextLikeImage.setImageResource( if(list.contains(position.toString())){R.drawable.like_click}else{R.drawable.like})
        }else{
            holder.binding.homePageVpItemRvTextLikeImage.setImageResource(R.drawable.like)
        }

        holder.binding.homePageVpItemRvTextAutograph.text=getItem(position)?.user?.signature
        holder.binding.homePageVpItemRvTextText.text=getItem(position)?.joke?.content

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:HomeRecommendVpItemRvItemLayoutBinding=HomeRecommendVpItemRvItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }


}