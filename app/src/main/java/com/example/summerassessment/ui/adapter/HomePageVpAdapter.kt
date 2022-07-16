package com.example.summerassessment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.R
import com.example.summerassessment.ui.mainactivity.MainActivity

class HomePageVpAdapter(val context: FragmentActivity, val adapter:HomeRecommendAdapter): RecyclerView.Adapter<HomePageVpAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val a:RecyclerView=itemView.findViewById(R.id.home_page_vp_item_rv)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate( R.layout.home_page_vp_item_layout,parent,false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position==1) {
            holder.a.layoutManager = LinearLayoutManager(context as MainActivity)
            holder.a.adapter = adapter
        }
    }


    override fun getItemCount(): Int =5

}