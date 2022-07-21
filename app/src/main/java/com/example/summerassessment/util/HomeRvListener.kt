package com.example.summerassessment.util

import android.util.Log
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.listener.VideoPlayListener
import com.example.summerassessment.ui.adapter.HomeAdapter
import kotlin.concurrent.thread

class HomeRvListener(private val listener: VideoPlayListener): RecyclerView.OnScrollListener(){

    private var first: Int = 0
    private var last: Int = 0



    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

        thread {
            if (first >recyclerView.size-3 &&newState == RecyclerView.SCROLL_STATE_IDLE) {
                Thread.sleep(500)
                (recyclerView.adapter as HomeAdapter).update()
            }
        }

        if (first < 0) {
            first = 0
        }
        if (first != last) {
            listener.pauseVideo(last, recyclerView)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                last = first
                listener.playVideo(first, recyclerView)
            }
        }

    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

        first = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() - 1

        if(recyclerView.scrollState==RecyclerView.SCROLL_STATE_SETTLING&&first!=last){
            listener.pauseVideo(last, recyclerView)
        }

    }
}