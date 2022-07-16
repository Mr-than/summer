package com.example.summerassessment.model

data class RecommendListBean(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
)

data class Data(
    val info: Info,
    val joke: Joke,
    val user: User
)

data class Info(
    val commentNum: Int,
    val disLikeNum: Int,
    val isAttention: Boolean,
    val isLike: Boolean,
    val isUnlike: Boolean,
    val likeNum: Int,
    val shareNum: Int
)

data class Joke(
    val addTime: String,
    val audit_msg: String,
    val content: String,
    val hot: Boolean,
    val imageSize: Any,
    val imageUrl: String,
    val jokesId: Int,
    val latitudeLongitude: Any,
    val showAddress: String,
    val thumbUrl: String,
    val type: Int,
    val userId: Int,
    val videoSize: Any,
    val videoTime: Int,
    val videoUrl: String
)

data class User(
    val avatar: String,
    val nickName: String,
    val signature: String,
    val userId: Int
)