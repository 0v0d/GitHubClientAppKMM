package com.example.github.client.kmm.mock

import com.example.github.client.kmm.SearchRepositoriesUseCaseHelper
import com.example.github.client.kmm.model.OwnerItem
import com.example.github.client.kmm.model.RepositoryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Suppress("UnusedPrivateMember")
class MockSearchRepositoriesUseCaseHelper : SearchRepositoriesUseCaseHelper() {
    override fun searchRepositories(query: String): Flow<List<RepositoryItem>> = flow {
        emit(
            listOf(
                RepositoryItem(
                    id = 123456789,
                    name = "Hello-World",
                    fullName = "octocat/Hello-World",
                    owner = OwnerItem(
                        login = "octocat",
                        avatarUrl = "https://avatars.githubusercontent.com/u/583231?v=4",
                        htmlUrl = "https://github.com/octocat"
                    ),
                    htmlUrl = "https://github.com/octocat/Hello-World",
                    description = "This is your first repository",
                    language = "Kotlin",
                    stargazersCount = "1500",
                    watchersCount = "1500",
                    forksCount = "300",
                    openIssuesCount = "42"
                ),
                RepositoryItem(
                    id = 987654321,
                    name = "Sample-Repo",
                    fullName = "johndoe/Sample-Repo",
                    owner = OwnerItem(
                        login = "johndoe",
                        avatarUrl = "https://avatars.githubusercontent.com/u/123456?v=4",
                        htmlUrl = "https://github.com/johndoe"
                    ),
                    htmlUrl = "https://github.com/johndoe/Sample-Repo",
                    description = "This is another sample repository",
                    language = "Java",
                    stargazersCount = "2500",
                    watchersCount = "2500",
                    forksCount = "500",
                    openIssuesCount = "75"
                )
            )
        )
    }
}
