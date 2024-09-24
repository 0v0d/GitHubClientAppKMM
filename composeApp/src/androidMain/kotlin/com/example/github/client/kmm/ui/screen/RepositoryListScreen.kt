package com.example.github.client.kmm.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.github.client.kmm.R
import com.example.github.client.kmm.mock.RepositoryItemMocks.mockRepoList
import com.example.github.client.kmm.model.RepositoryItem
import com.example.github.client.kmm.ui.theme.AppTheme
import com.example.github.client.kmm.util.Utility
import com.example.github.client.kmm.viewmodel.RepositoryListViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel

@Composable
fun RepositoryListScreen(
    inputText: String,
    onItemClick: (RepositoryItem) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = koinViewModel()
) {
    val repositories by viewModel.repositories.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()

    LaunchedEffect(inputText) {
        viewModel.searchRepositories(inputText)
    }

    RepositoryListContent(
        repositories = repositories.toImmutableList(),
        isLoading = loadingState,
        onItemClick = onItemClick,
        onRetry = { viewModel.searchRepositories(inputText) },
        modifier = modifier
    )
}

@Composable
fun RepositoryListContent(
    repositories: ImmutableList<RepositoryItem>,
    isLoading: Boolean,
    onItemClick: (RepositoryItem) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when {
            isLoading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

            repositories.isEmpty() -> ErrorContent(
                errorMessage = R.string.error_message,
                onRetry = onRetry
            )

            else -> RepositoryList(
                repositories = repositories,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun RepositoryList(
    repositories: ImmutableList<RepositoryItem>,
    onItemClick: (RepositoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(repositories) { repository ->
            RepositoryListItem(
                repository = repository,
                onClick = { onItemClick(repository) }
            )
        }
    }
}

@Composable
fun RepositoryListItem(
    repository: RepositoryItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        RepositoryItemContent(repository)
    }
}

@Composable
private fun RepositoryItemContent(
    repository: RepositoryItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(repository.owner.avatarUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = repository.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = repository.owner.login,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            repository.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = repository.stargazersCount,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row {
                    repository.language?.let {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = "Language",
                            modifier = Modifier.size(16.dp),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            color = Utility().getColorForLanguage(it)
                        )
                    }
                }
            }
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "View Details",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun RepositoryListPreview() {
    AppTheme {
        RepositoryListContent(
            repositories = mockRepoList.toImmutableList(),
            onItemClick = {},
            isLoading = false,
            onRetry = {}
        )
    }
}
