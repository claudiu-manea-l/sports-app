package com.codez.sportsapp.presentation.fixture.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codez.sportsapp.presentation.fixture.viewdata.FixtureType
import com.codez.sportsapp.presentation.fixture.viewdata.FixtureViewData
import com.codez.sportsapp.presentation.fixture.viewdata.dummyCompletedFixture
import com.codez.sportsapp.presentation.fixture.viewdata.dummyInProgressFixture
import com.codez.sportsapp.presentation.fixture.viewdata.dummyUpcomingFixture
import com.codez.sportsapp.presentation.fixture.viewdata.toDisplayString

@Composable
fun SimpleScoreboard(
    modifier: Modifier = Modifier,
    fixtureViewData: FixtureViewData
) {
    when (fixtureViewData.fixtureType) {
        is FixtureType.Upcoming -> UpcomingScoreboard(
            modifier = modifier,
            viewData = fixtureViewData.fixtureType
        )

        is FixtureType.InProgress -> IncomingScoreboard(
            modifier = modifier,
            viewData = fixtureViewData.fixtureType
        )

        is FixtureType.Completed -> CompletedScoreboard(
            modifier = modifier,
            viewData = fixtureViewData.fixtureType
        )
    }
}

@Composable
fun IncomingScoreboard(
    modifier: Modifier = Modifier,
    viewData: FixtureType.InProgress
) {
    Column(modifier = modifier) {
        ScoreboardBox(bgColor = Color.Red) {
            Text(
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                text = viewData.score.toDisplayString()
            )
        }
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            text = viewData.clock,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CompletedScoreboard(
    modifier: Modifier = Modifier,
    viewData: FixtureType.Completed
) {
    ScoreboardBox(
        modifier = modifier,
        bgColor = Color.Blue
    ) {
        Text(
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            text = viewData.score.toDisplayString()
        )
    }
}

@Composable
fun UpcomingScoreboard(
    modifier: Modifier = Modifier,
    viewData: FixtureType.Upcoming
) {
    ScoreboardBox(
        modifier = modifier,
        bgColor = Color.Gray
    ) {
        Text(
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            text = viewData.time
        )
    }
}

@Composable
private fun ScoreboardBox(
    modifier: Modifier = Modifier,
    bgColor: Color,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(4.dp),
                color = bgColor
            )
            .padding(
                top = 4.dp,
                bottom = 4.dp,
                start = 8.dp,
                end = 8.dp
            )
    ) {
        content()
    }
}

@Composable
@Preview
fun UpcomingScoreboardDemo() = SimpleScoreboard(
    fixtureViewData = dummyUpcomingFixture()
)

@Composable
@Preview
fun InProgressScoreboardDemo() = SimpleScoreboard(
    fixtureViewData = dummyInProgressFixture()
)

@Composable
@Preview
fun CompletedScoreboardDemo() = SimpleScoreboard(
    fixtureViewData = dummyCompletedFixture()
)