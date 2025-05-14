package com.codez.sportsapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FixtureDTO(
    @SerialName("kickoff") val kickoff: KickoffDTO,
    @SerialName("teams") val teams: List<TeamsDTO>,
    @SerialName("ground") val ground: GroundDTO,
    @SerialName("status") val status: MatchStatusDTO,
    @SerialName("attendance") val attendance: Int?,
    @SerialName("clock") val clock: ClockDTO,
    @SerialName("goals") val matchEvents: List<EventDTO>,
    @SerialName("id") val id: Int
)

@Serializable
data class KickoffDTO(
    @SerialName("label") val timeLabel: String,
    @SerialName("millis") val millis: Long
)

@Serializable
data class TeamsDTO(
    @SerialName("team") val team: TeamDTO,
    @SerialName("score") val score: Int
)

@Serializable
data class TeamDTO(
    @SerialName("id") val id: Int,
    @SerialName("club") val club: ClubDTO,
    @SerialName("name") val name: String,
    @SerialName("teamType") val teamType: String,
    @SerialName("shortName") val shortName: String
)

@Serializable
data class ClubDTO(
    @SerialName("abbr") val abbreviation: String
)

@Serializable
data class GroundDTO(
    @SerialName("name") val name: String,
    @SerialName("city") val city: String,
)

@Serializable
data class ClockDTO(
    @SerialName("secs") val secondsElapsed: Int,
    @SerialName("label") val time: String
)

@Serializable
enum class MatchStatusDTO {
    @SerialName("U")
    Upcoming,
    @SerialName("I")
    Live,
    @SerialName("C")
    Completed
}

@Serializable
data class EventDTO(
    @SerialName("personId") val playerId: Int?,
    @SerialName("assistId") val assistId: Int?,
    @SerialName("clock") val clock: ClockDTO,
    @SerialName("score") val score: ScoreDTO,
    @SerialName("type") val type: String,
    @SerialName("teamId") val teamId:Int?
)

@Serializable
data class ScoreDTO(
    @SerialName("homeScore") val home: Int,
    @SerialName("awayScore") val away: Int
)

enum class EventTypeDTO {
    @SerialName("G")
    Goal
}