package com.rure.angkorlife.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rure.angkorlife.presentation.screen.HomeScreen
import com.rure.angkorlife.presentation.screen.LoginScreen

const val MainNavRoute = "main"
fun NavGraphBuilder.mainNavGraph(navController: NavController, onScreenChanged: (Destination) -> Unit) {
    navigation(
        route = MainNavRoute,
        startDestination = Destination.Login.route
    ) {
        composable(route = Destination.Home.route) {
            HomeScreen()
            onScreenChanged(Destination.Home)
        }

        composable(route = Destination.Login.route) {
            LoginScreen(
                toHomeScreen = { navController.navigate(Destination.Home.route) }
            )
            onScreenChanged(Destination.Login)
        }

        composable(route = Destination.Profile.route) {
            onScreenChanged(Destination.Profile)
        }
    }
}