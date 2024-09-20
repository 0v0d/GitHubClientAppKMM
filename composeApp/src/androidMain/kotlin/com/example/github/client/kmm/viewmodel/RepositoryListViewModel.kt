package com.example.github.client.kmm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.client.kmm.model.RepositoryItem
import com.example.github.client.kmm.usecase.SearchRepositoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepositoryListViewModel(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : ViewModel() {
    private val _repositories = MutableStateFlow<List<RepositoryItem>>(emptyList())
    val repositories = _repositories.asStateFlow()

    private val _loadingState = MutableStateFlow(true)
    val loadingState = _loadingState.asStateFlow()

    private lateinit var lastQuery: String

    fun searchRepositories(inputText: String) {
        lastQuery = inputText
        _loadingState.value = true
        viewModelScope.launch {
            try {
                searchRepositoriesUseCase(inputText).collect { response ->
                    _repositories.value = response
                }
            } catch (e: Exception) {
                _repositories.value = emptyList()
            } finally {
                _loadingState.value = false
            }
        }
    }
}
