package com.example.github.client.kmm.module

import com.example.github.client.kmm.remote.GitHubAPI
import com.example.github.client.kmm.repository.GitHubRepository
import com.example.github.client.kmm.repository.GitHubRepositoryImpl
import com.example.github.client.kmm.usecase.SearchRepositoriesUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

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

val repositoryModule = module {
    single<GitHubRepository> { GitHubRepositoryImpl(get()) }
}

val sharedModule = listOf(
    networkModule,
    useCaseModule,
    repositoryModule
)
