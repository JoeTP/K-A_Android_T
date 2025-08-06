package com.example.khatibalamytask.data.di

import com.example.khatibalamytask.domain.repository.NewsRepository
import com.example.khatibalamytask.domain.usecase.GetHeadlinesUseCase
import com.example.khatibalamytask.domain.usecase.SearchNewsUseCase
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

    @Singleton
    @Provides
    fun provideGetLastSearchUseCase(repo : NewsRepository): SearchNewsUseCase {
        return SearchNewsUseCase(repo)
    }
}