package com.example.github.client.kmm

import com.example.github.client.kmm.module.sharedModule
import com.example.github.client.kmm.usecase.SearchRepositoriesUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class SearchRepositoriesUseCaseHelper : KoinComponent {
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase by inject()
    fun searchRepositories(query: String) = searchRepositoriesUseCase(query)
}

fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}