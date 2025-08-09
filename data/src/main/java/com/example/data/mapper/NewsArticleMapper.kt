package com.example.khatibalamytask.data.mapper

import com.example.khatibalamytask.core.utils.constants.AppStrings.Companion.NULL_STRING
import com.example.khatibalamytask.data.remote.model.NewsDto
import com.example.khatibalamytask.domain.model.NewsArticle

fun NewsDto.toDomain() : NewsArticle {
    return NewsArticle(
        title = title ?: NULL_STRING,
        description = description ?: NULL_STRING,
        url = url ?: NULL_STRING,
        publishedAt = publishedAt ?: NULL_STRING,
        sourceName = source?.name ?: NULL_STRING,
        urlToImage = urlToImage
    )
}