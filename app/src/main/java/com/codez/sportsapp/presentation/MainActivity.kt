package com.codez.sportsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codez.sportsapp.presentation.fixture.FixturesView
import com.codez.sportsapp.presentation.match.MatchView
import com.codez.sportsapp.ui.theme.SportsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportsAppTheme {
                MainContent()
            }
        }
    }
}

@Composable
private fun MainContent() {
    val navController = rememberNavController()


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            SportsAppNavBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Fixture.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Gray)
        ) {
            composable(Screen.Fixture.route) {
                FixturesView(onNavigateToMatch = { matchId ->
                    navController.navigate(Screen.Match.routeWithId(matchId))
                })
            }
            composable(
                route = Screen.Match.route,
                arguments = listOf(navArgument("matchId") { type = NavType.IntType })
            ) { backStackEntry ->
                val matchId = backStackEntry.arguments?.getInt("matchId") ?: -1
                MatchView(matchId = matchId)
            }
        }
    }
}

@Composable
private fun SportsAppNavBar(
    navController: NavController
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val previousRoute = navController.previousBackStackEntry?.destination?.route

    val screenTitleMap = mapOf(
        Screen.Match.route to "Match",
        Screen.Fixture.route to "Fixtures"
    )

    val currentScreenTitle = screenTitleMap[currentRoute] ?: ""
    val previousScreenTitle = screenTitleMap[previousRoute] ?: ""

    val canNavigateBack = previousRoute != null
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
    ) {
        if (canNavigateBack) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { navController.navigateUp() }
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = previousScreenTitle,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        Color.Blue
                    )
                )
            }
        }
        Text(
            text = currentScreenTitle,
            style = MaterialTheme.typography.titleLarge
                .copy(Color.Black),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}