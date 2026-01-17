package com.example.github.client.kmm.mock

import com.example.github.client.kmm.SearchRepositoriesUseCaseHelper
import com.example.github.client.kmm.data.mock.RepositoryItemMocks
import com.example.github.client.kmm.data.model.RepositoryItem

class MockSearchRepositoriesUseCaseHelper : SearchRepositoriesUseCaseHelper() {
    override suspend fun searchRepositories(query: String): List<RepositoryItem> =
        RepositoryItemMocks.mockRepoList
}
