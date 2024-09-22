package com.example.github.client.kmm.mock

import com.example.github.client.kmm.SearchRepositoriesUseCaseHelper
import com.example.github.client.kmm.model.RepositoryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Suppress("UnusedPrivateMember")
class MockSearchRepositoriesUseCaseHelper : SearchRepositoriesUseCaseHelper() {
    override fun searchRepositories(query: String): Flow<List<RepositoryItem>> = flow {
        emit(
            RepositoryItemMocks.mockRepoList
        )
    }
}
