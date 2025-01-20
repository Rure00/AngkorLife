package com.rure.angkorlife.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val MainNavRoute = "main"
fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(
        route = MainNavRoute,
        startDestination = Destination.Login.route
    ) {
        composable(route = Destination.Home.route) {

        }

        composable(route = Destination.Login.route) {

        }

        composable(route = Destination.Profile.route) {

        }
    }
}