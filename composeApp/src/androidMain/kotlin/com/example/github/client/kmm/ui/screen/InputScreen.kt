package com.example.github.client.kmm.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.github.client.kmm.viewmodel.InputViewModel
import com.example.github.client.kmm.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun InputScreen(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InputViewModel = koinViewModel()
) {
    val inputText by viewModel.inputText
    SearchTextField(
        query = inputText,
        onQueryChange = { newValue ->
            viewModel.updateInputText(newValue)
        },
        onSearch = {
            if (inputText.isNotEmpty()) {
                onSearch(inputText)
                viewModel.updateInputText("")
            }
        },
        modifier = modifier
    )
}

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun InputScreenPreview() {
    AppTheme {
        InputScreen(
            onSearch = {},
            modifier = Modifier,
            viewModel = InputViewModel()
        )
    }
}
