package com.codez.sportsapp.presentation.match

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codez.sportsapp.presentation.components.ErrorView
import com.codez.sportsapp.presentation.components.LoadingView
import com.codez.sportsapp.presentation.match.components.MatchBannerView
import com.codez.sportsapp.presentation.match.components.MatchEventRow
import com.codez.sportsapp.presentation.match.components.MatchInfoView
import com.codez.sportsapp.presentation.match.viewdata.MatchDetailsViewData
import com.codez.sportsapp.presentation.match.viewdata.dummyMatch


@Composable
fun MatchView(
    viewModel : MatchViewModel = hiltViewModel(),
    matchId:Int
) {
        val viewState by viewModel.viewState.collectAsState()
        when (viewState) {
            MatchDetailsViewState.Loading -> {
                LoadingView()
            }

            MatchDetailsViewState.Error -> {
                ErrorView()
            }

            is MatchDetailsViewState.ViewState -> {
                MatchDetailsCard((viewState as MatchDetailsViewState.ViewState).viewData)
            }
        }
}

@Composable
fun MatchDetailsCard(
    detailsViewData: MatchDetailsViewData
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.TopCenter)
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column {
                MatchBannerView(
                    homeTeamInfoViewData = detailsViewData.homeTeamInfoViewData,
                    awayTeamInfoViewData = detailsViewData.awayTeamInfoViewData
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    items(detailsViewData.events) { item ->
                        MatchEventRow(item)
                    }
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(1.dp)
                        .background(Color.Gray)
                )
                MatchInfoView(
                    matchInfoViewData = detailsViewData.matchInfo
                )
            }
        }
    }
}


@Composable
@Preview
private fun MatchDetailsCardPreview() = MatchDetailsCard(
    dummyMatch()
)


