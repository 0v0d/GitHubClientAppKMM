package com.example.github.client.kmm.repository

import com.example.github.client.kmm.model.GitHubAPIResponse
import com.example.github.client.kmm.remote.GitHubAPI
import io.github.reactivecircus.cache4k.Cache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

interface GitHubRepository {
    suspend fun getRepositories(query: String): GitHubAPIResponse?
}

class GitHubRepositoryImpl(
    private val service: GitHubAPI,
    private val cache: Cache<String, GitHubAPIResponse>
) : GitHubRepository {
    override suspend fun getRepositories(query: String) = searchRepositories(query)

    private suspend fun searchRepositories(query: String): GitHubAPIResponse? =
        withContext(Dispatchers.IO) {
            cache.get(query)?.let { return@withContext it }
            return@withContext try {
                val response = service.searchRepositories(query)
                cache.put(query, response)
                response
            } catch (e: Exception) {
                null
            }
        }
}
