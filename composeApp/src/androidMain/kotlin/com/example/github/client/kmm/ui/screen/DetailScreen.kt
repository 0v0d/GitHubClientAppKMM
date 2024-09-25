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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CallSplit
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.github.client.kmm.mock.RepositoryItemMocks
import com.example.github.client.kmm.model.OwnerItem
import com.example.github.client.kmm.model.RepositoryItem
import com.example.github.client.kmm.util.getColorForLanguage
import com.example.github.client.kmm.util.getFormattedCount

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
        RepositoryTitle(repositoryItem.name)

        Spacer(modifier = Modifier.height(8.dp))

        RepositoryOwnerInfo(repositoryItem.owner)

        Spacer(modifier = Modifier.height(16.dp))

        repositoryItem.description?.let {
            RepositoryDescription(it)
        }

        repositoryItem.language?.let {
            RepositoryLanguage(it)
        }

        Spacer(modifier = Modifier.height(8.dp))

        RepositoryStatistics(
            stars = getFormattedCount(repositoryItem.stargazersCount),
            watchers = getFormattedCount(repositoryItem.watchersCount),
            forks = getFormattedCount(repositoryItem.forksCount),
            issues = getFormattedCount(repositoryItem.openIssuesCount)
        )
    }
}

@Composable
fun RepositoryTitle(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun RepositoryOwnerInfo(owner: OwnerItem) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = owner.avatarUrl,
            contentDescription = "Owner Avatar",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = owner.login)
    }
}

@Composable
fun RepositoryDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun RepositoryLanguage(language: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Code,
            contentDescription = "Language"
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = language,
            color = getColorForLanguage(language)
        )
    }
}

@Composable
fun RepositoryStatistics(
    stars: String,
    watchers: String,
    forks: String,
    issues: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatisticItem(Icons.Default.Star, stars, "Stars")
        StatisticItem(Icons.Default.RemoveRedEye, watchers, "Watchers")
        StatisticItem(Icons.AutoMirrored.Filled.CallSplit, forks, "Forks")
        StatisticItem(Icons.Default.Adjust, issues, "Issues")
    }
}

@Composable
fun StatisticItem(
    icon: ImageVector,
    count: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = icon, contentDescription = label)
        Text(text = count)
        Text(text = label, style = MaterialTheme.typography.bodySmall)
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun PreviewDetailScreen() {
    DetailScreen(
        repositoryItem = RepositoryItemMocks.mockRepo1
    )
}
