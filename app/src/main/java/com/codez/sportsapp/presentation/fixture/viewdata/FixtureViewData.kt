package com.codez.sportsapp.presentation.fixture.viewdata

import com.codez.sportsapp.presentation.components.ImageSrc

data class FixtureViewData(
    val id: Int,
    val awayTeam: TeamNameLogoViewData,
    val homeTeam: TeamNameLogoViewData,
    val fixtureType: FixtureType,
)

data class TeamNameLogoViewData(
    val name:String,
    val logo:ImageSrc
)

sealed class FixtureType {
    data class Upcoming(
        val time:String
    ): FixtureType()

    data class InProgress(
        val score: ScoreViewData,
        val clock: String
    ) : FixtureType()

    data class Completed(
        val score: ScoreViewData
    ) : FixtureType()
}

data class ScoreViewData(
    val homeScore: Int,
    val awayScore: Int
)

fun ScoreViewData.toDisplayString() = "$homeScore - $awayScore"