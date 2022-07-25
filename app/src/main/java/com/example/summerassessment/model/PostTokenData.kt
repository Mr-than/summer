package com.example.summerassessment.model

data class PostTokenData(
    val code: Int,
    val `data`: DataN,
    val msg: String
)

data class DataN(
    val token: String
)


data class HashData(
    val hash: String,
    val key: String
)