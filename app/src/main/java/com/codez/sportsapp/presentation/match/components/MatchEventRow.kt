package com.codez.sportsapp.presentation.match.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codez.sportsapp.presentation.match.viewdata.MatchEventsViewData
import com.codez.sportsapp.presentation.match.viewdata.dummyEvent

@Composable
fun MatchEventRow(
    matchEventsViewData: MatchEventsViewData
){
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = matchEventsViewData.homeText,
            modifier = Modifier
                .weight(1f)
                .wrapContentSize(Alignment.CenterEnd)
                .padding(end = 8.dp),
            textAlign = TextAlign.Start
        )
        Text(
            text = matchEventsViewData.awayText,
            modifier = Modifier
                .weight(1f)
                .wrapContentSize(Alignment.CenterStart)
                .padding(start = 8.dp),
            textAlign = TextAlign.Start
        )
    }
}

@Preview
@Composable
fun HomeGoalMatchEventRow() =
    MatchEventRow(
        dummyEvent(true)
    )


@Preview
@Composable
fun AwayGoalMatchEventRow() =
    MatchEventRow(
        dummyEvent(false)
    )