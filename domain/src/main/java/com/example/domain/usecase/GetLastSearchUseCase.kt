package com.example.khatibalamytask.domain.usecase

import com.example.domain.usecase.UseCase
import com.example.khatibalamytask.domain.repository.NewsRepository

class GetLastSearchUseCase (private val repo : NewsRepository) : UseCase<Unit, String?> {
    override suspend fun invoke(params: Unit): String? {
        return repo.getLastSearch()
    }
}
