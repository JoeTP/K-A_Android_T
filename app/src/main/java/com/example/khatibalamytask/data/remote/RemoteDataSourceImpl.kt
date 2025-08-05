package com.example.khatibalamytask.data.remote

import jakarta.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : RemoteDataSource {

}