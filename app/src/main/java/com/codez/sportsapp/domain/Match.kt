package com.codez.sportsapp.domain

data class Fixture(
    val match:Match,
    val type: MatchType
)

data class Match(
    val matchId:Int,
    val matchInfo: MatchInfo,
    val events:List<MatchEvent>,
    val clock: Clock?
)

data class MatchUpdates(
    val events:List<MatchEvent>,
    val clock: Clock
)

data class MatchInfo(
    val homeTeam: Team,
    val awayTeam: Team,
    val matchDetails: MatchDetails
)

data class Team(
    val id:Int,
    val name: String,
    val abbreviation: String,
    val logoUrl:String
)

data class Score(
    val home:Int,
    val away:Int
)

data class MatchDetails(
    val kickoff:Kickoff,
    val location: Location,
    val score: Score,
    val halfTimeScore: Score?,
    val attendance: Int?,
    val referee: String?
)

data class Location(
    val name: String,
    val city: String
)

data class Kickoff(
    val timeInMillis: Long,
    val timeLabel: TimeLabel
)

data class MatchEvent(
    val clock: Clock,
    val score:Score,
    val type:String,
    val playerName:String?,
    val teamId:Int?
)

data class Clock(
    val seconds: Int,
    val timeLabel: TimeLabel
)

data class TimeLabel(val value:String)

sealed class MatchType {
    data object Upcoming : MatchType()
    data object InProgress : MatchType()
    data object Completed : MatchType()
}