package com.codez.sportsapp.presentation.fixture.viewdata

import com.codez.sportsapp.R
import com.codez.sportsapp.presentation.components.ImageSrc

fun dummyUpcomingFixture() =
    FixtureViewData(
        id = -1,
        homeTeam = dummyHomeTeam(),
        awayTeam = dummyAwayTeam(),
        fixtureType = FixtureType.Upcoming(
            time = "20:00"
        )
    )

fun dummyInProgressFixture() = FixtureViewData(
    id = -1,
    homeTeam = dummyHomeTeam(),
    awayTeam = dummyAwayTeam(),
    fixtureType = FixtureType.InProgress(
        score = dummyScore(),
        clock = "50'"
    )
)

fun dummyCompletedFixture() = FixtureViewData(
    id = -1,
    homeTeam = dummyHomeTeam(),
    awayTeam = dummyAwayTeam(),
    fixtureType = FixtureType.Completed(
        score = dummyScore()
    )
)

private fun dummyHomeTeam() = TeamNameLogoViewData(
    name = "Arsenal",
    logo = ImageSrc.Resource(R.drawable.home_logo)
)

private fun dummyAwayTeam() = TeamNameLogoViewData(
    name = "Brighton",
    logo = ImageSrc.Resource(R.drawable.away_logo)
)

private fun dummyScore() = ScoreViewData(
    homeScore = 3,
    awayScore = 1
)