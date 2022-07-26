package com.example.summerassessment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.R
import com.example.summerassessment.model.DataO

/**
 *   description:系统信息的adapter
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
class MessageAdapter(val list: ArrayList<DataO>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val t:TextView=itemView.findViewById(R.id.message_content)
        val v:TextView=itemView.findViewById(R.id.message_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_message_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.t.text=list[position].content
        holder.v.text=list[position].timeStr
    }

    override fun getItemCount(): Int {
        return list.size
    }


}