package com.codez.sportsapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchesResponse(
    @SerialName("teams") val teams: List<TeamsDTO>,
    @SerialName("teamLists") val teamLists: List<TeamListsDTO>,
    @SerialName("status") val status: MatchStatusDTO,
    @SerialName("matchOfficials") val matchOfficials: List<MatchOfficialDTO>,
    @SerialName("kickoff") val kickoff: KickoffDTO,
    @SerialName("id") val id:Int,
    @SerialName("ground") val ground: GroundDTO,
    @SerialName("events") val events: List<EventDTO>,
    @SerialName("clock") val clock: ClockDTO,
    @SerialName("attendance") val attendance:Int?,
    @SerialName("halfTimeScore") val halfTimeScore : ScoreDTO
)

@Serializable
data class TeamListsDTO(
    @SerialName("teamId") val teamId: Int,
    @SerialName("lineup") val lineup: List<PlayerInfoDTO>,
    @SerialName("substitutes") val substitutes: List<PlayerInfoDTO>,
)

@Serializable
data class PlayerInfoDTO(
     @SerialName("id") val id: Int,
     @SerialName("age") val age: String,
     @SerialName("name") val name: NameDTO
)

@Serializable
data class MatchOfficialDTO(
    @SerialName("id") val id: Int,
    @SerialName("matchOfficialId") val matchOfficialId: Int,
    @SerialName("role") val role: String?,
    @SerialName("name") val name: NameDTO,
)

@Serializable
data class NameDTO(
    @SerialName("display") val display: String,
    @SerialName("first") val first: String,
    @SerialName("last") val last: String
)