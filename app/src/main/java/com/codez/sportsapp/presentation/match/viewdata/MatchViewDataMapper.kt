package com.codez.sportsapp.presentation.match.viewdata

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.codez.sportsapp.domain.Match
import com.codez.sportsapp.domain.MatchEvent
import com.codez.sportsapp.domain.MatchInfo
import com.codez.sportsapp.domain.Team
import com.codez.sportsapp.presentation.components.ImageSrc
import com.codez.sportsapp.presentation.emptyAnnotatedString

fun Match.toViewData(): MatchDetailsViewData =
    MatchDetailsViewData(
        homeTeamInfoViewData = matchInfo.homeTeam.toViewData(
            current = matchInfo.matchDetails.score.home,
            halfTime = matchInfo.matchDetails.halfTimeScore?.home ?: 0
        ),
        awayTeamInfoViewData = matchInfo.awayTeam.toViewData(
            current = matchInfo.matchDetails.score.away,
            halfTime = matchInfo.matchDetails.halfTimeScore?.away ?: 0
        ),
        events = events.toViewData(homeTeamId = matchInfo.homeTeam.id),
        matchInfo = matchInfo.toViewData()
    )

private fun Team.toViewData(current:Int,halfTime:Int) : TeamInfoViewData =
    TeamInfoViewData(
        name = abbreviation,
        logo = ImageSrc.Remote(logoUrl),
        currentScore =current,
        halfTimeScore = halfTime
    )

private fun List<MatchEvent>.toViewData(
    homeTeamId: Int
) : List<MatchEventsViewData>
= mapNotNull { it.toViewData(homeTeamId) }

private fun MatchEvent.toViewData(
    homeTeamId: Int
) : MatchEventsViewData? {
    return teamId?.let {
        val eventText = buildEventText()
        if(homeTeamId == teamId){
            MatchEventsViewData(
                homeText = eventText,
                awayText = emptyAnnotatedString()
            )
        } else {
            MatchEventsViewData(
                homeText = emptyAnnotatedString(),
                awayText = eventText
            )
        }
    }
}

private fun MatchEvent.buildEventText() = buildAnnotatedString {
    append("${clock.timeLabel.value}  ")
    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
        val event = when (type) {
            "G" -> "Goal"
            "P" -> "Penalty"
            else -> type
        }
        append("$event  ")
    }
    append("$playerName")
}


private fun MatchInfo.toViewData() : MatchInfoViewData {
    val timeAndDate = matchDetails.kickoff.timeLabel.value
    val date = timeAndDate.split(",").first()
    val time = timeAndDate.split(",").last()
    return MatchInfoViewData(
        kickOff = time,
        date = date,
        location = matchDetails.location.name,
        attendance = matchDetails.attendance,
        referee = matchDetails.referee
    )
}