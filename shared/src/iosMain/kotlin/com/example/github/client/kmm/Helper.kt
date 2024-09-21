package com.example.github.client.kmm

import com.example.github.client.kmm.module.sharedModule
import com.example.github.client.kmm.usecase.SearchRepositoriesUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

open class SearchRepositoriesUseCaseHelper : KoinComponent {
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase by inject()
    open fun searchRepositories(query: String) = searchRepositoriesUseCase(query)
}

@Suppress("UnusedPrivateMember")
fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}