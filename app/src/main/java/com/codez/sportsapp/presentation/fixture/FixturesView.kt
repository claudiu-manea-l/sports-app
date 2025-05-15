package com.codez.sportsapp.presentation.fixture

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.codez.sportsapp.presentation.components.ErrorView
import com.codez.sportsapp.presentation.components.LoadingView
import com.codez.sportsapp.presentation.fixture.components.IOSLikeSearchBar
import com.codez.sportsapp.presentation.fixture.components.MatchRow

@Composable
fun FixturesView(
    fixturesViewModel: FixturesViewModel = hiltViewModel(),
    onNavigateToMatch: (Int) -> Unit
) {

    val viewState by fixturesViewModel.viewState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (viewState) {
            FixturesViewState.Loading -> {
                LoadingView()
            }

            FixturesViewState.Error -> {
                ErrorView()
            }

            is FixturesViewState.ViewState -> {
                FixturesListView(
                    fixturesViewData = (viewState as FixturesViewState.ViewState).viewData,
                    onItemClick = { onNavigateToMatch(it) }
                )
            }
        }
    }
}

@Composable
private fun FixturesListView(
    fixturesViewData: FixturesViewData,
    onItemClick: (Int) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    IOSLikeSearchBar(
        query = searchQuery,
        onQueryChange = { searchQuery = it }
    )
    LazyColumn {
        items(fixturesViewData.fixtures) {
            MatchRow(it, onItemClick)
        }
    }
}