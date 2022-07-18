package com.example.summerassessment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.summerassessment.R
import com.example.summerassessment.listener.VideoPlayListener
import com.example.summerassessment.ui.mainactivity.MainActivity
import com.example.summerassessment.util.VideoIsPlay

class HomePageVpAdapter(
    private val context: FragmentActivity,
    private val adapters:(Int)->HomeAdapter
) : RecyclerView.Adapter<HomePageVpAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val a: RecyclerView = itemView.findViewById(R.id.home_page_vp_item_rv)
        private val refresh: SwipeRefreshLayout =
            itemView.findViewById(R.id.home_page_vp_item_rv_refresh)
        init {


            refresh.setOnRefreshListener {
                val position=layoutPosition

                adapters(position).refresh()
            }

            for(position in 0 until 5) {
                adapters(position).addLoadStateListener {
                    when (it.refresh) {
                        is LoadState.NotLoading -> {
                            refresh.isRefreshing = false
                        }
                        is LoadState.Loading -> {
                            refresh.isRefreshing = true
                        }
                        is LoadState.Error -> {
                            val state = it.refresh as LoadState.Error
                            refresh.isRefreshing = false
                            Toast.makeText(
                                context,
                                "Load Error: ${state.error.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_page_vp_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val manager = LinearLayoutManager(context as MainActivity)
            holder.a.layoutManager = manager
            holder.a.adapter = adapters(position)

            holder.a.addOnScrollListener(VideoIsPlay(adapters(position).getListener()))

    }

    override fun getItemCount(): Int = 5
}