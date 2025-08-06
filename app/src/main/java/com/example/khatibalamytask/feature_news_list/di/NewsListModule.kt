package com.example.khatibalamytask.feature_news_list.di

import com.example.khatibalamytask.domain.repository.NewsRepository
import com.example.khatibalamytask.domain.usecase.GetHeadlinesUseCase
import com.example.khatibalamytask.feature_news_list.NewsListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsListModule {

    @Singleton
    @Provides
    fun provideGetHeadlinesUseCase(repo : NewsRepository): GetHeadlinesUseCase {
        return GetHeadlinesUseCase(repo)
    }
}