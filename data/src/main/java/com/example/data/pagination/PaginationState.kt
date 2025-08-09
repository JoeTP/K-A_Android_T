package com.example.khatibalamytask.data.pagination

data class PaginationState(
    val currentPage: Int = 1,
    val pageSize: Int = 20,
    val isLastPage: Boolean = false,
    val isLoading: Boolean = false
)