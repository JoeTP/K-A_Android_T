package com.example.khatibalamytask.data.remote.model

data class NewsResponse (val status: String, val totalResults: Int, val articles: List<NewsDto>)