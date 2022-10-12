package com.company.jetreaderapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.company.jetreaderapp.screens.details.ReaderBookDetailsScreen
import com.company.jetreaderapp.screens.home.HomeScreenViewModel
import com.company.jetreaderapp.screens.home.ReaderHomeScreen
import com.company.jetreaderapp.screens.login.ReaderLoginScreen
import com.company.jetreaderapp.screens.search.BookSearchViewModel
import com.company.jetreaderapp.screens.search.ReaderSearchScreen
import com.company.jetreaderapp.screens.splash.ReaderSplashScreen
import com.company.jetreaderapp.screens.stats.ReaderStatsScreen
import com.company.jetreaderapp.screens.update.ReaderUpdateScreen

@ExperimentalComposeUiApi
@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreensList.ReaderSplashScreen.name){
        composable(ScreensList.ReaderSplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }

        composable(ScreensList.ReaderHomeScreen.name) {
            val viewModel = hiltViewModel<HomeScreenViewModel>()
            ReaderHomeScreen(navController = navController, homeScreenViewModel = viewModel)
        }

        composable(ScreensList.ReaderLoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }

        val detailName = ScreensList.ReaderBookDetailsScreen.name
        composable("$detailName/{bookId}",
            arguments = listOf(navArgument("bookId"){
                type = NavType.StringType
            })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let {
                ReaderBookDetailsScreen(navController = navController, bookId = it.toString())
            }

        }

        composable(ScreensList.ReaderSearchScreen.name) {
            val viewModel = hiltViewModel<BookSearchViewModel>()
            ReaderSearchScreen(navController = navController, viewModel)
        }

        composable(ScreensList.ReaderStatsScreen.name) {
            ReaderStatsScreen(navController = navController)
        }

        val updateName = ScreensList.ReaderUpdateScreen.name
        composable(
            route = "$updateName/{bookItemId}",
            arguments = listOf(navArgument("bookItemId") {
                type = NavType.StringType
            })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookItemId").let {
                val viewModel = hiltViewModel<HomeScreenViewModel>()
                ReaderUpdateScreen(
                    navController = navController,
                    bookItemId = it.toString(),
                    viewModel = viewModel)
            }
        }

    }
}