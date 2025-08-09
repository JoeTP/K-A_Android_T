package com.example.khatibalamytask.data.remote.model

data class NewsDto(
    val title: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceDto?,
    val urlToImage: String?,
    val url: String?
)