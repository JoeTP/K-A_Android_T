package com.example.khatibalamytask.domain.usecase

import com.example.khatibalamytask.core.utils.UseCase
import com.example.khatibalamytask.domain.repository.NewsRepository
import javax.inject.Inject

class GetLastSearchUseCase @Inject constructor(private val repo : NewsRepository) : UseCase<Unit, String?> {
    override suspend fun invoke(params: Unit): String? {
        return repo.getLastSearch()
    }
}
