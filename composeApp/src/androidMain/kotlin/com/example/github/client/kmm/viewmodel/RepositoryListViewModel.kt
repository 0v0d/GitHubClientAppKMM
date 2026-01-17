package com.example.github.client.kmm.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.client.kmm.R
import com.example.github.client.kmm.data.model.RepositoryItem
import com.example.github.client.kmm.usecase.SearchRepositoriesUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepositoryListViewModel(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RepositoryListUIState>(RepositoryListUIState.Loading)
    val uiState = _uiState.asStateFlow()

    private lateinit var lastQuery: String

    fun searchRepositories(inputText: String) {
        lastQuery = inputText
        viewModelScope.launch {
            try {
                val result = searchRepositoriesUseCase(inputText)
                _uiState.value = when {
                    result.isNotEmpty() -> RepositoryListUIState.Success(result.toImmutableList())
                    else -> RepositoryListUIState.Error(R.string.error_message_not_found_repository)
                }
            } catch (_: Exception) {
                _uiState.value = RepositoryListUIState.Error(R.string.error_message_unknown)
            }
        }
    }
}

sealed class RepositoryListUIState {
    object Loading : RepositoryListUIState()
    data class Success(val repositories: ImmutableList<RepositoryItem>) : RepositoryListUIState()
    data class Error(@StringRes val messageID: Int) : RepositoryListUIState()
}
