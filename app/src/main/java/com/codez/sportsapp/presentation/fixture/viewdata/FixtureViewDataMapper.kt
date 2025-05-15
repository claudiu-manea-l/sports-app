package com.codez.sportsapp.presentation.fixture.viewdata

import com.codez.sportsapp.domain.Clock
import com.codez.sportsapp.domain.Fixture
import com.codez.sportsapp.domain.Match
import com.codez.sportsapp.domain.MatchInfo
import com.codez.sportsapp.domain.MatchType
import com.codez.sportsapp.domain.Team
import com.codez.sportsapp.presentation.components.ImageSrc
import com.codez.sportsapp.presentation.fixture.FixturesViewData

fun List<Fixture>.toViewData() =
    FixturesViewData(
        fixtures = map { it.toViewData() }
    )

fun Fixture.toViewData() = FixtureViewData(
    id = match.matchId,
    fixtureType = type.toFixtureType(match),
    awayTeam = match.matchInfo.awayTeam.toViewData(),
    homeTeam = match.matchInfo.homeTeam.toViewData(),
)

fun MatchType.toFixtureType(
    match: Match
): FixtureType {
    return when (this) {
        is MatchType.Completed -> FixtureType.Completed(
            score = match.matchInfo.toScoreViewData()
        )

        is MatchType.InProgress -> FixtureType.InProgress(
            score = match.matchInfo.toScoreViewData(),
            clock = match.clock.toViewData()
        )

        is MatchType.Upcoming -> FixtureType.Upcoming(
            time = match.matchInfo.matchDetails.kickoff.timeLabel.value.split(",")[1]
        )
    }
}

fun MatchInfo.toScoreViewData() = ScoreViewData(
    homeScore = matchDetails.score.home,
    awayScore = matchDetails.score.away
)

fun Clock?.toViewData() = this?.let {
    timeLabel.value
} ?: "0'"

private fun Team.toViewData() = TeamNameLogoViewData(
    name = name,
    logo = ImageSrc.Remote(url = logoUrl)
)