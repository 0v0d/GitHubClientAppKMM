package com.example.github.client.kmm.module

import com.example.github.client.kmm.model.GitHubAPIResponse
import com.example.github.client.kmm.remote.GitHubAPI
import com.example.github.client.kmm.repository.GitHubRepository
import com.example.github.client.kmm.repository.GitHubRepositoryImpl
import com.example.github.client.kmm.usecase.SearchRepositoriesUseCase
import io.github.reactivecircus.cache4k.Cache
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import kotlin.time.Duration.Companion.minutes

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    single {
        GitHubAPI(
            baseUrl = "https://api.github.com/search/repositories",
            httpClient = get()
        )
    }
}

val useCaseModule = module {
    factory { SearchRepositoriesUseCase(get()) }
}

val cacheModule = module {
    single {
        Cache.Builder<String, GitHubAPIResponse>()
            .expireAfterWrite(10.minutes)
            .build()
    }
}
val repositoryModule = module {
    single<GitHubRepository> { GitHubRepositoryImpl(get(), get()) }
}

val sharedModule = listOf(
    networkModule,
    useCaseModule,
    repositoryModule,
    cacheModule
)
