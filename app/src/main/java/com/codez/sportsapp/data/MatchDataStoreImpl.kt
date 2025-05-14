package com.codez.sportsapp.data

import com.codez.sportsapp.domain.Clock
import com.codez.sportsapp.domain.Kickoff
import com.codez.sportsapp.domain.Location
import com.codez.sportsapp.domain.Match
import com.codez.sportsapp.domain.MatchDetails
import com.codez.sportsapp.domain.MatchEvent
import com.codez.sportsapp.domain.MatchInfo
import com.codez.sportsapp.domain.MatchUpdates
import com.codez.sportsapp.domain.Score
import com.codez.sportsapp.domain.Team
import com.codez.sportsapp.domain.TimeLabel
import com.codez.sportsapp.domain.stores.MatchDataStore
import com.codez.sportsapp.domain.usecase.FailedToFetchMatchException
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

private const val IMAGE_BASE_URL =
    "https://pyates-twocircles.github.io/two-circles-tech-test/images"

class RemoteDataStoreImpl @Inject constructor(
    private val serviceAPI: ServiceAPI
) : MatchDataStore {

    override suspend fun getMatch(id: Int): Match {
        return fetchMatchData().toMatch()
    }

    override suspend fun getMatchUpdates(id: Int): MatchUpdates {
        return fetchMatchData().latestEvents()
    }

    private suspend fun fetchMatchData():MatchesResponse {
       try {
            return serviceAPI.getMatchData()
       } catch (e:HttpException){
           throw FailedToFetchMatchException(cause = e)
       }
    }
}


private fun MatchesResponse.toMatch(): Match =
    Match(
        matchId = id,
        matchInfo = MatchInfo(
            homeTeam = teams.last().team.toDomainModel(),
            awayTeam = teams.first().team.toDomainModel(),
            matchDetails = extractMatchDetails()
        ),
        clock = clock.toDomainModel(),
        events = events.toDomainModel(players = allPlayers())
    )

private fun MatchesResponse.extractMatchDetails(): MatchDetails {
    return MatchDetails(
        kickoff = kickoff.toDomainModel(),
        location = ground.toDomainModel(),
        score = Score(
            away = teams.first().score,
            home = teams.last().score
        ),
        halfTimeScore = Score(
            home = halfTimeScore.home,
            away = halfTimeScore.away
        ),
        attendance = attendance,
        referee = matchOfficials.find { it.role == "MAIN" }?.name?.display
    )
}

private fun MatchesResponse.latestEvents(): MatchUpdates {
    return MatchUpdates(
        events = events.toDomainModel(players = allPlayers()),
        clock = clock.toDomainModel()
    )
}

private fun MatchesResponse.allPlayers(): List<PlayerInfoDTO> =
    teamLists.flatMap { it.lineup + it.substitutes }


private fun List<EventDTO>.toDomainModel(
    players: List<PlayerInfoDTO>
): List<MatchEvent> =
    map { it.toDomainModel(players) }

private fun EventDTO.toDomainModel(
    players: List<PlayerInfoDTO>
): MatchEvent {
    return MatchEvent(
        clock = clock.toDomainModel(),
        score = score.toDomainModel(),
        type = type,
        teamId = teamId,
        playerName = players.find { it.id == playerId }?.name?.first
    )
}

private fun ClockDTO.toDomainModel(): Clock =
    Clock(
        seconds = secondsElapsed,
        timeLabel = TimeLabel(
            value = time
        )
    )

private fun ScoreDTO.toDomainModel(): Score =
    Score(
        away = away,
        home = home
    )

private fun TeamDTO.toDomainModel(): Team =
    Team(
        id = id,
        name = name,
        abbreviation = club.abbreviation,
        logoUrl = "$IMAGE_BASE_URL/$id.png"
    )

private fun KickoffDTO.toDomainModel(): Kickoff =
    Kickoff(
        timeInMillis = millis,
        timeLabel = TimeLabel(value = timeLabel)
    )

private fun GroundDTO.toDomainModel(): Location =
    Location(
        name = name,
        city = city
    )