package com.example.github.client.kmm.presentation.navigation

import androidx.annotation.StringRes
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.github.client.kmm.R
import com.example.github.client.kmm.presentation.navigation.GithubRepoDestination.GithubRepoDetailScreen
import com.example.github.client.kmm.presentation.navigation.GithubRepoDestination.GithubRepoInputScreen
import com.example.github.client.kmm.presentation.navigation.GithubRepoDestination.GithubRepoListScreen

@StringRes
fun NavBackStackEntry?.getScreenTitleResID(): Int = when {
    this.isRoute<GithubRepoInputScreen>() ->
        R.string.list_screen_title

    this.isRoute<GithubRepoListScreen>() ->
        R.string.list_screen_title

    this.isRoute<GithubRepoDetailScreen>() ->
        R.string.detail_screen_title

    //その他の画面
    else -> R.string.app_name
}

inline fun <reified T : Any> NavBackStackEntry?.isRoute(): Boolean =
    this?.destination?.hasRoute(route = T::class) == true

fun NavHostController.navigateSingleTopTo(route: GithubRepoDestination) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
