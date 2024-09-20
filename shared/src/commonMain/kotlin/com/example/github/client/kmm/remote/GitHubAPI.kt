package com.example.github.client.kmm.remote

import com.example.github.client.kmm.model.GitHubAPIResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders

class GitHubAPI(
    private val baseUrl: String,
    private val httpClient: HttpClient
) {
    suspend fun searchRepositories(query: String): GitHubAPIResponse {
        return httpClient.get {
            url(baseUrl)
            parameter("q", query)
            header(HttpHeaders.Accept, "application/vnd.github.v3+json")
        }.body()
    }
}
