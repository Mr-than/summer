package com.example.summerassessment.model

data class BrushVideoData(
    val code: Int,
    val `data`: List<DataA>,
    val msg: String
)

data class DataA(
    val info: InfoA,
    val joke: JokeA,
    val user: UserA
)

data class InfoA(
    val commentNum: Int,
    val disLikeNum: Int,
    val isAttention: Boolean,
    val isLike: Boolean,
    val isUnlike: Boolean,
    val likeNum: Int,
    val shareNum: Int
)

data class JokeA(
    val addTime: String,
    val audit_msg: String,
    val content: String,
    val hot: Boolean,
    val imageSize: Any,
    val imageUrl: String,
    val jokesId: Int,
    val latitudeLongitude: String,
    val showAddress: String,
    val thumbUrl: String,
    val type: Int,
    val userId: Int,
    val videoSize: String,
    val videoTime: Int,
    val videoUrl: String
)

data class UserA(
    val avatar: String,
    val nickName: String,
    val signature: String,
    val userId: Int
)