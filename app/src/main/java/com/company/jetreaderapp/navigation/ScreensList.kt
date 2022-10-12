package com.company.jetreaderapp.navigation

import java.lang.IllegalArgumentException

enum class ScreensList {
    ReaderBookDetailsScreen,
    ReaderHomeScreen,
    ReaderLoginScreen,
    ReaderSearchScreen,
    ReaderSplashScreen,
    ReaderStatsScreen,
    ReaderUpdateScreen,
    ReaderSignupScreen;

    companion object {
        fun fromRoute(route: String?) : ScreensList =
            when(route?.substringBefore("/")) {
                ReaderBookDetailsScreen.name -> ReaderBookDetailsScreen
                ReaderHomeScreen.name -> ReaderHomeScreen
                ReaderLoginScreen.name -> ReaderLoginScreen
                ReaderSearchScreen.name -> ReaderSearchScreen
                ReaderSplashScreen.name -> ReaderSplashScreen
                ReaderStatsScreen.name -> ReaderStatsScreen
                ReaderUpdateScreen.name -> ReaderUpdateScreen
                ReaderSignupScreen.name -> ReaderSignupScreen
                null -> ReaderHomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}