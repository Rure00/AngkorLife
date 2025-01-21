package com.rure.angkorlife.presentation.navigation

sealed class Destination(
    val route: String,
    val showBackButton: Boolean
) {
    data object Login: Destination("login", false)
    data object Home: Destination("home", true)
    data object Profile: Destination("profile", true)
}