package com.example.summerassessment.ui.adapter.follow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.R


/**
 *   description:当前用户关注，粉丝的vp的adapter
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
class FollowFanAdapter(
    private val context: FragmentActivity,
    val list: ArrayList<FollowFanRvAdapter>
) : RecyclerView.Adapter<FollowFanAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rv: RecyclerView = itemView.findViewById(R.id.vp2_follow_fan_item_rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vp2_activity_follow_fan_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rv.layoutManager = LinearLayoutManager(context)
        holder.rv.adapter = list[position]
    }

    override fun getItemCount(): Int = 2

}