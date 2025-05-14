package com.codez.sportsapp.presentation.fixture

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FixturesView(
    onNavigateToMatch: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { onNavigateToMatch(1) }
        ) {
            Text("Go to match")
        }
    }
}