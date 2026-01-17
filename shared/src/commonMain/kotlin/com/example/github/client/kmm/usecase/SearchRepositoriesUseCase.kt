package com.example.github.client.kmm.usecase

import com.example.github.client.kmm.data.model.RepositoryItem
import com.example.github.client.kmm.data.model.toDomainModel
import com.example.github.client.kmm.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoriesUseCase(private val repository: GitHubRepository) {
    suspend operator fun invoke(query: String): List<RepositoryItem>{
        try {
            val response = repository.getRepositories(query)
            return response?.items?.map { it.toDomainModel() } ?: emptyList()
        } catch (_: Exception) {
           return emptyList()
        }
    }
}
