package com.example.github.client.kmm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.github.client.kmm.data.model.RepositoryItem
import com.example.github.client.kmm.presentation.navigation.GithubRepoDestination.GithubRepoDetailScreen
import com.example.github.client.kmm.presentation.navigation.GithubRepoDestination.GithubRepoInputScreen
import com.example.github.client.kmm.presentation.navigation.GithubRepoDestination.GithubRepoListScreen
import com.example.github.client.kmm.presentation.ui.screen.DetailScreen
import com.example.github.client.kmm.presentation.ui.screen.InputScreen
import com.example.github.client.kmm.presentation.ui.screen.RepositoryListScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = GithubRepoInputScreen,
        modifier = modifier
    ) {
        composable<GithubRepoInputScreen> {
            InputScreen(
                onSearch = { keyWord ->
                    navController.navigateSingleTopTo(
                        GithubRepoListScreen(keyWord)
                    )
                }
            )
        }
        composable<GithubRepoListScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<GithubRepoListScreen>()
            RepositoryListScreen(
                inputText = args.query,
                onItemClick = { repositoryData ->
                    navController.navigateSingleTopTo(
                        GithubRepoDetailScreen(
                            id = repositoryData.id,
                            name = repositoryData.name,
                            fullName = repositoryData.fullName,
                            ownerLogin = repositoryData.ownerLogin,
                            ownerAvatarUrl = repositoryData.ownerAvatarUrl,
                            ownerHtmlUrl = repositoryData.ownerHtmlUrl,
                            htmlUrl = repositoryData.htmlUrl,
                            description = repositoryData.description,
                            language = repositoryData.language,
                            stargazersCount = repositoryData.stargazersCount,
                            watchersCount = repositoryData.watchersCount,
                            forksCount = repositoryData.forksCount,
                            openIssuesCount = repositoryData.openIssuesCount
                        )
                    )
                },
            )
        }
        composable<GithubRepoDetailScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<GithubRepoDetailScreen>()
            DetailScreen(
                repositoryItem = RepositoryItem(
                    id = args.id,
                    name = args.name,
                    fullName = args.fullName,
                    ownerLogin = args.ownerLogin,
                    ownerAvatarUrl = args.ownerAvatarUrl,
                    ownerHtmlUrl = args.ownerHtmlUrl,
                    htmlUrl = args.htmlUrl,
                    description = args.description,
                    language = args.language,
                    stargazersCount = args.stargazersCount,
                    watchersCount = args.watchersCount,
                    forksCount = args.forksCount,
                    openIssuesCount = args.openIssuesCount
                )
            )
        }
    }
}
