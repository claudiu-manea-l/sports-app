package com.codez.sportsapp.presentation.fixture.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.codez.sportsapp.presentation.components.SportsImage
import com.codez.sportsapp.presentation.fixture.viewdata.FixtureType
import com.codez.sportsapp.presentation.fixture.viewdata.FixtureViewData
import com.codez.sportsapp.presentation.fixture.viewdata.dummyCompletedFixture
import com.codez.sportsapp.presentation.fixture.viewdata.dummyInProgressFixture
import com.codez.sportsapp.presentation.fixture.viewdata.dummyUpcomingFixture

@Composable
fun MatchRow(
    fixtureViewData: FixtureViewData,
    onItemClick: (Int) -> Unit = { }
) {
    val textColor =
        if (fixtureViewData.fixtureType is FixtureType.InProgress) Color.White else Color.Black
    val bgColor =
        if (fixtureViewData.fixtureType is FixtureType.InProgress) Color.Magenta else Color.White
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .clickable { onItemClick(fixtureViewData.id) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)

    ) {
        ConstraintLayout(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            val (scoreboard, homeName, homeLogo, awayName, awayLogo) = createRefs()
            SimpleScoreboard(
                modifier = Modifier.constrainAs(scoreboard) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                fixtureViewData = fixtureViewData
            )
            SportsImage(
                imageSrc = fixtureViewData.homeTeam.logo,
                contentDescription = "Home team logo",
                modifier = Modifier
                    .constrainAs(homeLogo) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(scoreboard.start)
                    }
            )
            Text(
                text = fixtureViewData.homeTeam.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = textColor
                ),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .constrainAs(homeName){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(homeLogo.start)
                    }
            )
            SportsImage(
                imageSrc = fixtureViewData.awayTeam.logo,
                contentDescription = "Away team logo",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .constrainAs(awayLogo) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(scoreboard.end)
                    }
            )
            Text(
                text = fixtureViewData.awayTeam.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = textColor
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .constrainAs(awayName){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(awayLogo.end)
                    }
            )
        }
    }
}

@Composable
@Preview
fun UpcomingMatchRowDemo() = MatchRow(
    fixtureViewData = dummyUpcomingFixture()
)

@Composable
@Preview
fun InProgressMatchRowDemo() = MatchRow(
    fixtureViewData = dummyInProgressFixture()
)

@Composable
@Preview
fun CompletedMatchRowDemo() = MatchRow(
    fixtureViewData = dummyCompletedFixture()
)
