package com.example.github.client.kmm.module

import com.example.github.client.kmm.viewmodel.InputViewModel
import com.example.github.client.kmm.viewmodel.RepositoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


private val repositoryListViewModel = module {
    viewModel { RepositoryListViewModel(get()) }
}

private val inputViewModel = module {
    viewModel { InputViewModel() }
}

val androidModule = listOf(
    inputViewModel,
    repositoryListViewModel,
)
