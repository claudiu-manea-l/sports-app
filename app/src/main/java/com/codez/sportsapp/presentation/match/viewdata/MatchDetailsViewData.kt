package com.codez.sportsapp.presentation.match.viewdata

import androidx.compose.ui.text.AnnotatedString
import com.codez.sportsapp.presentation.components.ImageSrc

data class MatchDetailsViewData(
    val homeTeamInfoViewData: TeamInfoViewData,
    val awayTeamInfoViewData: TeamInfoViewData,
    val events: List<MatchEventsViewData>,
    val matchInfo: MatchInfoViewData
)

data class TeamInfoViewData(
    val name: String,
    val logo: ImageSrc,
    val currentScore: Int,
    val halfTimeScore: Int
)


data class MatchEventsViewData(
    val homeText: AnnotatedString,
    val awayText: AnnotatedString
)

data class MatchInfoViewData(
    val kickOff: String,
    val date: String,
    val location: String,
    val attendance: Int?,
    val referee: String?
)