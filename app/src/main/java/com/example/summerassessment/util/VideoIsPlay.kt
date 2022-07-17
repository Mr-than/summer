package com.example.summerassessment.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summerassessment.listener.VideoPlayListener

class VideoIsPlay(private val listener: VideoPlayListener): RecyclerView.OnScrollListener(){

    private var first: Int = 0
    private var last: Int = 0


    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
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