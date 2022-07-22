package com.example.summerassessment.ui.adapter.userinfoadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.R
import com.example.summerassessment.databinding.ActivityVp2LayoutBinding
import com.example.summerassessment.model.Data
import com.example.summerassessment.ui.userpage.UserInfoActivity
import com.example.summerassessment.ui.userpage.UserInfoViewModel

class UserInfoChildAdapter(private val context: Context, private val id: Int) :
    RecyclerView.Adapter<UserInfoChildAdapter.ViewHolder>() {


    class ViewHolder(val binding: ActivityVp2LayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private val viewModel: UserInfoViewModel by lazy {
        ViewModelProvider(context as UserInfoActivity).get(
            UserInfoViewModel::class.java
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ActivityVp2LayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            val adapter=UserTextImgAdapter(context)
            holder.binding.activityVp2Rv.layoutManager = LinearLayoutManager(context)
            holder.binding.activityVp2Rv.adapter = adapter

            viewModel.textImgLiveData.observe(context as UserInfoActivity){
                adapter.submitList(it)
            }
            viewModel.getTextImg(id)

        } else if (position == 1) {
            val adapter=UserPageVideoAdapter(context)
            holder.binding.activityVp2Rv.layoutManager = LinearLayoutManager(context)
            holder.binding.activityVp2Rv.adapter = adapter

            viewModel.videoLiveData.observe(context as UserInfoActivity){
                adapter.submitList(it)
            }
            viewModel.getVideo(id)
        }

    }

    override fun getItemCount(): Int {
        return 2
    }

}