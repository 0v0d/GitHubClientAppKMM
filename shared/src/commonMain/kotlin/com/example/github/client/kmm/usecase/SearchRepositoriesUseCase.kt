package com.example.github.client.kmm.usecase

import com.example.github.client.kmm.model.RepositoryItem
import com.example.github.client.kmm.model.toDomainModel
import com.example.github.client.kmm.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoriesUseCase(private val repository: GitHubRepository) {
    operator fun invoke(query: String): Flow<List<RepositoryItem>> = flow {
        try {
            val response = repository.getRepositories(query)
            if (response != null) {
                emit(response.items.map { it.toDomainModel() })
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}
