package com.codez.sportsapp.presentation.match.viewdata

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.codez.sportsapp.R
import com.codez.sportsapp.presentation.components.ImageSrc

fun dummyMatch() = MatchDetailsViewData(
    homeTeamInfoViewData = dummyHomeTeam(),
    awayTeamInfoViewData = dummyAwayTeam(),
    events = listOf(
        dummyEvent(),
        dummyEvent(),
        dummyEvent(false),
        dummyEvent()
    ),
    matchInfo = MatchInfoViewData(
        kickOff = "14:00",
        date = "Sun 24 Nov 2024",
        location = "St Mary's Stadium, Southampton",
        attendance = 30000,
        referee = "Samuel Barrott"
    )
)

fun dummyEvent(isHomeEvent: Boolean = true) : MatchEventsViewData {
    val dummyText = buildAnnotatedString {
        append("42'  ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Goal  ")
        }
        append("Saka")
    }
    val emptyString = buildAnnotatedString { append("") }
    return if (isHomeEvent) {
        MatchEventsViewData(
            homeText = dummyText,
            awayText = emptyString
        )
    } else {
        MatchEventsViewData(
            homeText = emptyString,
            awayText = dummyText
        )
    }
}

fun dummyHomeTeam() = TeamInfoViewData(
    name = "ARS",
    logo = ImageSrc.Resource(R.drawable.home_logo),
    currentScore = 3,
    halfTimeScore = 1
)

fun dummyAwayTeam() = TeamInfoViewData(
    name = "BRI",
    logo = ImageSrc.Resource(R.drawable.away_logo),
    currentScore = 1,
    halfTimeScore = 1
)