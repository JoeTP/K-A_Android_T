package com.example.khatibalamytask.data.mapper

import com.example.khatibalamytask.core.utils.constants.STRING_NULL
import com.example.khatibalamytask.data.remote.model.NewsDto
import com.example.khatibalamytask.domain.model.NewsArticle


fun NewsDto.toDomain() : NewsArticle {
    return NewsArticle(
        title = title ?: STRING_NULL,
        description = description ?: STRING_NULL,
        url = url ?: STRING_NULL,
        publishedAt = publishedAt ?: STRING_NULL,
        sourceName = source?.name ?: STRING_NULL,
        urlToImage = imageUrl ?: STRING_NULL
    )
}