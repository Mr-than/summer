package com.example.summerassessment.model

data class CommentData(
    val code: Int,
    val `data`: DataS,
    val msg: String
)

data class DataS(
    val comments: List<Comment>,
    val count: Int
)

data class Comment(
    val commentId: Int,
    val commentUser: CommentUser,
    val content: String,
    val isLike: Boolean,
    val itemCommentList: Any,
    val itemCommentNum: Int,
    val jokeId: Int,
    val jokeOwnerUserId: Int,
    val likeNum: Int,
    val timeStr: String
)

data class CommentUser(
    val nickname: String,
    val userAvatar: String,
    val userId: Int
)