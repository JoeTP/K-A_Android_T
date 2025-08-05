package com.example.khatibalamytask.data.mapper

import com.example.khatibalamytask.core.utils.constants.STRING_NULL
import com.example.khatibalamytask.data.remote.model.NewsDto
import com.example.khatibalamytask.domain.model.NewsArticle


fun NewsDto.toDomain() : NewsArticle {
    return NewsArticle(
        title = title,
        description = description,
        url = url,
        publishedAt = publishedAt,
        sourceName = source.name,
        urlToImage = imageUrl ?: STRING_NULL
    )
}