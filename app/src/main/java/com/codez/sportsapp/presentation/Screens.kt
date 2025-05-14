package com.codez.sportsapp.presentation

sealed class Screen(
    val route: String
) {
    data object Fixture : Screen("fixtures")
    data object Match : Screen("match/{matchId}") {
        fun routeWithId(matchId:Int) = "match/$matchId"
    }
}