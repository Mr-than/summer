package com.example.summerassessment.model

data class MessageData(
    val code: Int,
    val `data`: List<DataO>,
    val msg: String
)

data class DataO(
    val content: String,
    val id: Int,
    val isRead: Boolean,
    val targetId: Int,
    val timeStr: String,
    val title: String,
    val type: Int
)