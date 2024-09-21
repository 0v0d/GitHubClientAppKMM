package com.example.github.client.kmm.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CallSplit
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.github.client.kmm.model.OwnerItem
import com.example.github.client.kmm.model.RepositoryItem
import com.example.github.client.kmm.util.Utility

@Composable
fun DetailScreen(
    repositoryItem: RepositoryItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderView(repositoryItem)
        Spacer(modifier = Modifier.height(16.dp))
        OwnerView(repositoryItem.owner)
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionView(repositoryItem.description)
        Spacer(modifier = Modifier.height(16.dp))
        StatisticsView(repositoryItem)
    }
}

@Composable
private fun HeaderView(repositoryItem: RepositoryItem) {
    Column {
        Text(
            text = repositoryItem.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        repositoryItem.language?.let { language ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = language,
                style = MaterialTheme.typography.bodyMedium,
                color = Utility().getColorForLanguage(language)
            )
        }
    }
}

@Composable
private fun OwnerView(owner: OwnerItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        AsyncImage(
            model = owner.avatarUrl,
            contentDescription = "Owner Avatar",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = owner.login,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Owner",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
private fun DescriptionView(description: String?) {
    description?.let {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
private fun StatisticsView(repositoryItem: RepositoryItem) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatisticItem(Icons.Default.Star, repositoryItem.stargazersCount, "Stars")
                StatisticItem(Icons.Default.RemoveRedEye, repositoryItem.watchersCount, "Watchers")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatisticItem(
                    Icons.AutoMirrored.Filled.CallSplit,
                    repositoryItem.forksCount,
                    "Forks"
                )
                StatisticItem(Icons.Default.ErrorOutline, repositoryItem.openIssuesCount, "Issues")
            }
        }
    }
}

@Composable
private fun StatisticItem(
    icon: ImageVector,
    count: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun PreviewDetailScreen() {
    DetailScreen(
        repositoryItem = RepositoryItem(
            id = 123456789,
            name = "Hello-World",
            fullName = "octocat/Hello-World",
            owner = OwnerItem(
                login = "octocat",
                avatarUrl = "https://avatars.githubusercontent.com/u/583231?v=4",
                htmlUrl = "https://github.com/octocat"
            ),
            htmlUrl = "https://github.com/octocat/Hello-World",
            description = "This is your first repository",
            language = "Kotlin",
            stargazersCount = "1500",
            watchersCount = "1500",
            forksCount = "300",
            openIssuesCount = "42"
        ),
    )
}
