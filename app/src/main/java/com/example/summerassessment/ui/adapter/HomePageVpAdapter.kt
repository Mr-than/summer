package com.example.summerassessment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.summerassessment.R
import com.example.summerassessment.ui.mainactivity.MainActivity
import com.example.summerassessment.util.HomeRvListener

class HomePageVpAdapter(
    private val context: FragmentActivity,
    private val list:List<HomeAdapter>,
) : RecyclerView.Adapter<HomePageVpAdapter.ViewHolder>() {

    class ViewHolder(
        private val context: FragmentActivity,
        itemView: View,
        list:List<HomeAdapter>
    ) : RecyclerView.ViewHolder(itemView) {
        val a: RecyclerView = itemView.findViewById(R.id.home_page_vp_item_rv)
        private val refresh: SwipeRefreshLayout = itemView.findViewById(R.id.home_page_vp_item_rv_refresh)

        init {
            refresh.setOnRefreshListener {
                val position = layoutPosition
                val a=list[position].refresh()
                refresh.isRefreshing=!a
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            context,
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_page_vp_item_layout, parent, false), list
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val manager = LinearLayoutManager(context as MainActivity)

        holder.a.layoutManager = manager
        holder.a.adapter = list[position]

        holder.a.addOnScrollListener(HomeRvListener(list[position].getListener()))

    }

    override fun getItemCount(): Int = 5
}