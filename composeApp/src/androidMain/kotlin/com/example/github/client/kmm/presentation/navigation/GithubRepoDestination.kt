package com.example.github.client.kmm.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class GithubRepoDestination {
    @Serializable
    data object GithubRepoInputScreen : GithubRepoDestination()

    @Serializable
    data class GithubRepoListScreen(val query: String) : GithubRepoDestination()

    //TODO: idもしくはusername/repoNameでdetail側でAPI叩くように修正する
    //RepositoryItemについても必要な情報だけを持つように変更する
    @Serializable
    data class GithubRepoDetailScreen(
        val id: Long,
        val name: String,
        val fullName: String,
        val ownerLogin: String,
        val ownerAvatarUrl: String,
        val ownerHtmlUrl: String,
        val htmlUrl: String,
        val description: String?,
        val language: String?,
        val stargazersCount: Int,
        val watchersCount: Int,
        val forksCount: Int,
        val openIssuesCount: Int
    ) : GithubRepoDestination()
}