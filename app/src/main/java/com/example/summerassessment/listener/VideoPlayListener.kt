package com.example.summerassessment.listener

import androidx.recyclerview.widget.RecyclerView

interface VideoPlayListener {
    fun playVideo(index:Int,recyclerView: RecyclerView)
    fun pauseVideo(index:Int,recyclerView: RecyclerView)
}