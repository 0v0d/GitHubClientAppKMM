package com.example.github.client.kmm.repository

import com.example.github.client.kmm.model.GitHubAPIResponse
import com.example.github.client.kmm.remote.GitHubAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext


interface GitHubRepository {
    suspend fun getRepositories(query: String): GitHubAPIResponse?
}

class GitHubRepositoryImpl(
    private val service: GitHubAPI,
) : GitHubRepository {

    override suspend fun getRepositories(query: String) = searchRepositories(query)

    private suspend fun searchRepositories(query: String): GitHubAPIResponse? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                service.searchRepositories(query)
            } catch (e: Exception) {
                null
            }
        }
}
