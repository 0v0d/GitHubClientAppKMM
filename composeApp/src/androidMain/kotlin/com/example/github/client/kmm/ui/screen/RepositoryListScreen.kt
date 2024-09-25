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
import com.example.github.client.kmm.util.getColorForLanguage
import com.example.github.client.kmm.util.getFormattedCount
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
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row {
                OwnerAvatarContent(repository.owner.avatarUrl)
                Spacer(modifier = Modifier.width(8.dp))
                RepositoryHeaderContent(
                    repository.name,
                    repository.owner.login
                )
            }
            repository.description?.let {
                DescriptionContent(it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            RepositoryStatsContent(
                repository.language,
                repository.stargazersCount
            )
        }

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "View Details",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun OwnerAvatarContent(avatarUrl: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(avatarUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
    )
}

@Composable
private fun RepositoryHeaderContent(name: String, ownerLogin: String) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = ownerLogin,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Composable
private fun DescriptionContent(description: String) {
    Spacer(modifier = Modifier.height(4.dp)) // 説明の前に少しスペース
    Text(
        text = description,
        style = MaterialTheme.typography.bodySmall,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun RepositoryStatsContent(language: String?, stars: Int) {
// スターとプログラミング言語の表示
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = getFormattedCount(stars),
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.width(16.dp))
        // プログラミング言語の表示
        language?.let {
            LanguageContent(it)
        }
    }
}

@Composable
private fun LanguageContent(language: String) {
    Icon(
        imageVector = Icons.Default.Code,
        contentDescription = "Language",
        modifier = Modifier.size(16.dp)
    )
    Spacer(modifier = Modifier.width(4.dp))
    Text(
        text = language,
        style = MaterialTheme.typography.bodySmall,
        color = getColorForLanguage(language)
    )
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
