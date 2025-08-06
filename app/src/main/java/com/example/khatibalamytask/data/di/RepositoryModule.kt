package com.example.khatibalamytask.data.di

import com.example.khatibalamytask.data.local.LocalDataSource
import com.example.khatibalamytask.data.local.LocalDataSourceImpl
import com.example.khatibalamytask.data.local.PreferencesManager
import com.example.khatibalamytask.data.remote.NewsApiService
import com.example.khatibalamytask.data.remote.RemoteDataSource
import com.example.khatibalamytask.data.remote.RemoteDataSourceImpl
import com.example.khatibalamytask.data.repository.NewsRepositoryImpl
import com.example.khatibalamytask.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(newsApiService: NewsApiService): RemoteDataSource = RemoteDataSourceImpl(newsApiService)


    @Provides
    @Singleton
    fun provideLocalDataSource(preferencesManager: PreferencesManager): LocalDataSource = LocalDataSourceImpl(preferencesManager)

    @Provides
    @Singleton
    fun provideNewsRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): NewsRepository {
        return NewsRepositoryImpl(remoteDataSource, localDataSource)
    }
}