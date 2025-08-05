package com.example.khatibalamytask.data.repository

import com.example.khatibalamytask.data.local.LocalDataSource
import com.example.khatibalamytask.data.remote.RemoteDataSource
import com.example.khatibalamytask.domain.repository.NewsRepository
import jakarta.inject.Inject

class NewsRepositoryImpl  @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {
}