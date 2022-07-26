package com.example.summerassessment.ui.adapter.followfanadapter

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.summerassessment.R
import com.example.summerassessment.databinding.Vp2ActivityFollowFanRvItemLayoutBinding
import com.example.summerassessment.databinding.Vp2VideoRvItemBinding
import com.example.summerassessment.model.Data
import com.example.summerassessment.model.DataQ
import com.example.summerassessment.ui.ffactivity.FollowsFansViewModel


/**
 *   description:当前用户关注，粉丝的rv的adapter
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
class FollowFanRvAdapter(private val context: FragmentActivity,private val num:Int) :
    ListAdapter<DataQ, FollowFanRvAdapter.ViewHolder>(CALL_BACK) {

    private val viewModel: FollowsFansViewModel by lazy {
        ViewModelProvider(context).get(
            FollowsFansViewModel::class.java
        )
    }

    companion object {

        val CALL_BACK = object : DiffUtil.ItemCallback<DataQ>() {
            override fun areItemsTheSame(oldItem: DataQ, newItem: DataQ): Boolean {
                return oldItem.userId == newItem.userId
            }

            override fun areContentsTheSame(oldItem: DataQ, newItem: DataQ): Boolean {
                return oldItem == newItem
            }
        }

    }

    inner class ViewHolder(val binding: Vp2ActivityFollowFanRvItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            binding.vp2FollowFanItemRvButton.setOnClickListener {


                val position = layoutPosition


                val drawable: Drawable? = ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.shape_follow_button,
                    null
                )


                binding.vp2FollowFanItemRvButton.background = drawable
                binding.vp2FollowFanItemRvButton.setTextColor(Color.parseColor("#FFFFFF"))
                binding.vp2FollowFanItemRvButton.text = "+关注"

                viewModel.setFollow(getItem(position).userId.toString(), "0")

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            Vp2ActivityFollowFanRvItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(num==2){
            holder.binding.vp2FollowFanItemRvButton.visibility=View.GONE
        }

        holder.binding.vp2FollowFanItemRvNickName.text = getItem(position).nickname
        holder.binding.vp2FollowFanItemRvContent.text = getItem(position).signature
        Glide.with(context).load(getItem(position).avatar).apply(
            RequestOptions.bitmapTransform(
                CircleCrop()
            )
        ).into(holder.binding.vp2FollowFanItemRvImg)


    }

}