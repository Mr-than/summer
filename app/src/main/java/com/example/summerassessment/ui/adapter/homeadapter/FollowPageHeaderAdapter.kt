package com.example.summerassessment.ui.adapter.homeadapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.summerassessment.R
import com.example.summerassessment.databinding.HomePageRvHeaderItemLayoutBinding
import com.example.summerassessment.model.DataX
import java.util.ArrayList

class FollowPageHeaderAdapter(private val context: Context, private val list: List<DataX>) :
    RecyclerView.Adapter<FollowPageHeaderAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: HomePageRvHeaderItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.homePageRvHeaderButtonItemFollow.setOnClickListener {
                val drawable: Drawable? = ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.shape_followed_button,
                    null
                )
                binding.homePageRvHeaderButtonItemFollow.background = drawable
                binding.homePageRvHeaderButtonItemFollow.setTextColor(Color.parseColor("#8A8A8A"))
                binding.homePageRvHeaderButtonItemFollow.text = "已关注"
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomePageRvHeaderItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.isNotEmpty()) {
            holder.binding.homePageRvHeaderItemNikeName.text = list[position].nickname
            holder.binding.homePageRvHeaderItemArticle.text = "发表 ${list[position].jokesNum}"
            holder.binding.homePageRvHeaderItemFan.text = "粉丝 ${list[position].fansNum}"

            list[position].avatar.run {
                Glide.with(context).load(this)
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .into(holder.binding.homePageRvHeaderItemPhoto)
            }

        }
    }

    fun setList(list: List<DataX>){
        (this.list as ArrayList).run{
            clear()
            addAll(list)
        }
    }


    override fun getItemCount(): Int = 6

}