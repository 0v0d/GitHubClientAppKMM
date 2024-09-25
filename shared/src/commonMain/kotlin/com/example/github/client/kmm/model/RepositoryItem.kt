package com.example.github.client.kmm.model

import kotlinx.serialization.Serializable

@Serializable
data class RepositoryItem(
    val id: Long,
    val name: String,
    val fullName: String,
    val owner: OwnerItem,
    val htmlUrl: String,
    val description: String?,
    val language: String?,
    val stargazersCount: Int,
    val watchersCount: Int,
    val forksCount: Int,
    val openIssuesCount: Int
)

@Serializable
data class OwnerItem(
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String
)
