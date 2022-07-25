package com.example.summerassessment.ui.adapter.homeadapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.databinding.HomePageRvHeaderLayoutBinding
import com.example.summerassessment.model.Data
import com.example.summerassessment.model.DataX
import com.example.summerassessment.ui.homefragment.HomeFragmentViewModel
import com.example.summerassessment.ui.mainactivity.MainActivity
import java.util.ArrayList

class FollowPageAdapter(
    private val context: Context,
    private val viewModel: HomeFragmentViewModel,
    private val dataList: ArrayList<Data>
) : HomeAdapter(context,0) {

    private var isPlay = false


    private val adapter = FollowPageHeaderAdapter(context, ArrayList<DataX>())


    class HeaderViewHolder(val binding: HomePageRvHeaderLayoutBinding) : ViewHolder(binding.root)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (holder is HeaderViewHolder) {
            val manager = LinearLayoutManager(context)
            manager.orientation = LinearLayoutManager.HORIZONTAL
            holder.binding.homePageRvHeaderRv.layoutManager = manager
            holder.binding.homePageRvHeaderRv.adapter=adapter

            viewModel.recommendFollowListLiveData.observe(context as MainActivity) {
                refreshFollow(it)
            }
            viewModel.getRecommendFollowData()

        } else {
            super.onBindViewHolder(holder, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == HEADER) {
            return HeaderViewHolder(
                HomePageRvHeaderLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return HEADER
        }
        return super.getItemViewType(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshFollow(list: List<DataX>) {
        //因为这里数据量小，耗费的性能小，并且每次返回的数据都不一样，所以直接全部刷新
        adapter.setList(list)
        adapter.notifyDataSetChanged()
    }

    override fun update(i:Boolean){
        if(!isPlay)
        viewModel.getFollowData(i)
    }


    override fun playVideo(index: Int, recyclerView: RecyclerView) {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(index)
        if (viewHolder is VideoViewHolder) {
            isPlay = true
            viewHolder.binding.homePageVpItemRvVideoPlayer.start()
        }
    }

    override fun pauseVideo(index: Int, recyclerView: RecyclerView) {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(index)
        if (viewHolder is VideoViewHolder) {
            isPlay = false
            viewHolder.binding.homePageVpItemRvVideoPlayer.release()
        }
    }

    override fun refresh(close: () -> Unit): Boolean {
        viewModel.getFollowData(true)
        viewModel.getRecommendFollowData()
        return true
    }


}