package com.example.domain.usecase.params

data class SearchNewsParams(val query: String, val loadMore: Boolean = false)