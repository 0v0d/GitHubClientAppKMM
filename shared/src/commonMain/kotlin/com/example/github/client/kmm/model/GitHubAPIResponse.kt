package com.example.github.client.kmm.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubAPIResponse(
    @SerialName("items")
    val items: List<APIRepositoryItem>
)

@Serializable
data class APIRepositoryItem(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("owner")
    val owner: APIOwnerItem,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("description")
    val description: String?,
    @SerialName("language")
    val language: String?,
    @SerialName("stargazers_count")
    val stargazersCount: Int,
    @SerialName("watchers_count")
    val watchersCount: Int,
    @SerialName("forks_count")
    val forksCount: Int,
    @SerialName("open_issues_count")
    val openIssuesCount: Int
) 

@Serializable
data class APIOwnerItem(
    @SerialName("login")
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("html_url")
    val htmlUrl: String
) 

fun APIRepositoryItem.toDomainModel() = RepositoryItem(
    id = id,
    name = name,
    fullName = fullName,
    owner = owner.toDomainModel(),
    htmlUrl = htmlUrl,
    description = description,
    language = language,
    stargazersCount = stargazersCount.toString(),
    watchersCount = watchersCount.toString(),
    forksCount = forksCount.toString(),
    openIssuesCount = openIssuesCount.toString()
)

fun APIOwnerItem.toDomainModel() = OwnerItem(
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl
)
