package com.rure.angkorlife.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.rure.angkorlife.presentation.screen.HomeScreen
import com.rure.angkorlife.presentation.screen.LoginScreen
import com.rure.angkorlife.presentation.screen.ProfileScreen

const val MainNavRoute = "main"
fun NavGraphBuilder.mainNavGraph(navController: NavController, onScreenChanged: (Destination) -> Unit) {
    navigation(
        route = MainNavRoute,
        startDestination = Destination.Login.route
    ) {
        composable(route = Destination.Home.route) {
            HomeScreen {
                navController.navigate(Destination.Profile.route + "/${it.candidateId}")
            }
            onScreenChanged(Destination.Home)
        }

        composable(route = Destination.Login.route) {
            LoginScreen(
                toHomeScreen = { navController.navigate(Destination.Home.route) }
            )
            onScreenChanged(Destination.Login)
        }

        composable(
            route = Destination.Profile.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            val candidateId = it.arguments?.getInt("id") ?: throw  Exception("No Arguments For id.")
            ProfileScreen(candidateId)
            onScreenChanged(Destination.Profile)
        }
    }
}