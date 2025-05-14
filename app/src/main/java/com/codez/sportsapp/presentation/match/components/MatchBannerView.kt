package com.codez.sportsapp.presentation.match.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.codez.sportsapp.presentation.components.SportsImage
import com.codez.sportsapp.presentation.match.viewdata.TeamInfoViewData
import com.codez.sportsapp.presentation.match.viewdata.dummyAwayTeam
import com.codez.sportsapp.presentation.match.viewdata.dummyHomeTeam


@Composable
fun MatchBannerView(
    modifier: Modifier= Modifier,
    homeTeamInfoViewData: TeamInfoViewData,
    awayTeamInfoViewData: TeamInfoViewData
) {
    ConstraintLayout(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (homeIcon, homeName, scoreBoard, awayIcon, awayName) = createRefs()
        SportsImage(
            imageSrc = homeTeamInfoViewData.logo,
            contentDescription = "Home Team logo",
            modifier = Modifier
                .constrainAs(homeIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        TeamTitleText(
            teamName = homeTeamInfoViewData.name,
            modifier = Modifier.constrainAs(homeName) {
                top.linkTo(homeIcon.top)
                bottom.linkTo(homeIcon.bottom)
                start.linkTo(homeIcon.end, margin = 8.dp)
            }
        )

        SportsImage(
            imageSrc = awayTeamInfoViewData.logo,
            contentDescription = "Away Team logo",
            modifier = Modifier
                .constrainAs(awayIcon) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )
        TeamTitleText(
            teamName = awayTeamInfoViewData.name,
            modifier = Modifier
                .constrainAs(awayName) {
                top.linkTo(awayIcon.top)
                bottom.linkTo(awayIcon.bottom)
                end.linkTo(awayIcon.start, margin = 8.dp)
            }
        )
        TeamScoreBoard(
            homeTeamInfoViewData = homeTeamInfoViewData,
            awayTeamInfoViewData = awayTeamInfoViewData,
            modifier = Modifier.constrainAs(scoreBoard) {
                top.linkTo(parent.top)
                start.linkTo(homeName.end)
                end.linkTo(awayName.start)
            }
        )
    }
}

@Composable
fun TeamTitleText(
    modifier: Modifier,
    teamName: String
) {
    Text(
        modifier = modifier,
        text = teamName.uppercase(),
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun TeamScoreBoard(
    modifier: Modifier,
    homeTeamInfoViewData: TeamInfoViewData,
    awayTeamInfoViewData: TeamInfoViewData
){
    val currentScore = "" + homeTeamInfoViewData.currentScore + " - " + awayTeamInfoViewData.currentScore
    val htSCore = "HT " + awayTeamInfoViewData.halfTimeScore + " - "  + awayTeamInfoViewData.halfTimeScore
    Column(
        modifier = modifier
            .background(
                color = Color(0xFF000080),
                shape = RoundedCornerShape(8.dp)

            )
            .padding(12.dp),
        verticalArrangement = Arrangement.Center
    ){
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally),
            text = currentScore,
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.White
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = htSCore,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 10.sp,
                color = Color.White
            ),
            textAlign = TextAlign.Center

        )
    }
}

@Composable
@Preview
fun TeamScoreBoardDemo() = TeamScoreBoard(
    modifier = Modifier,
    homeTeamInfoViewData = dummyHomeTeam(),
    awayTeamInfoViewData = dummyAwayTeam()
)

@Composable
@Preview
fun MatchBannerViewDemo() = MatchBannerView(
    homeTeamInfoViewData = dummyHomeTeam(),
    awayTeamInfoViewData = dummyAwayTeam()
)