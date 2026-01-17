package com.example.github.client.kmm.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RepositoryItem(
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
)
