package com.example.summerassessment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.summerassessment.R
import com.example.summerassessment.model.Data

class HomeRecommendAdapter : PagingDataAdapter<Data,HomeRecommendAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR= object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.user == newItem.user
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val a: TextView =itemView.findViewById(R.id.home_page_vp_item_rv_text_nike_name)
        val b:ImageView=itemView.findViewById(R.id.home_page_vp_item_rv_text_photo)
        val c:ImageView=itemView.findViewById(R.id.home_page_vp_item_rv_text_like_image)
        val d:LottieAnimationView=itemView.findViewById(R.id.home_page_vp_item_rv_text_like_lottie)

        init {


            c.setOnClickListener{
                val position=layoutPosition
                d.visibility= VISIBLE
                d.setAnimation("like.json")
                d.playAnimation()
            }


        }

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.a.text=getItem(position)?.user?.nickName
            if(position%2==0)
            holder.b.visibility=GONE
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_recommend_vp_item_rv_item_layout,parent,false))
    }


}