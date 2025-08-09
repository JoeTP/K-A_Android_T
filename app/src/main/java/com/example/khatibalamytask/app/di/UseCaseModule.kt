package com.example.khatibalamytask.data.di

import com.example.khatibalamytask.domain.repository.NewsRepository
import com.example.khatibalamytask.domain.usecase.GetHeadlinesUseCase
import com.example.khatibalamytask.domain.usecase.GetLastSearchUseCase
import com.example.khatibalamytask.domain.usecase.SearchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetHeadlinesUseCase(repo : NewsRepository): GetHeadlinesUseCase {
        return GetHeadlinesUseCase(repo)
    }

    @Singleton
    @Provides
    fun provideSearchNewsUseCaseUseCase(repo : NewsRepository): SearchNewsUseCase {
        return SearchNewsUseCase(repo)
    }

    @Singleton
    @Provides
    fun provideGetLastSearchUseCase(repo : NewsRepository): GetLastSearchUseCase{
        return GetLastSearchUseCase(repo)
    }
}