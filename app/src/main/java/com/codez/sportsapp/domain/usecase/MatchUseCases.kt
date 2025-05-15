package com.codez.sportsapp.domain.usecase

import com.codez.sportsapp.domain.Match
import com.codez.sportsapp.domain.stores.MatchDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class FilterOutGoals @Inject constructor() {
    operator fun invoke(
        matchFlow: Flow<Match>
    ): Flow<Match> =
        matchFlow.map { match ->
            match.copy(
                events = match.events.filter { event ->
                    event.type == "G" || event.type == "P"
                }
            )
        }
}

class GetMatch @Inject constructor(
    private val matchDataStore: MatchDataStore,
    private val applyMatchUpdates: ApplyMatchUpdates,
    private val getPeriodicTrigger: GetPeriodicTrigger
) {
    operator fun invoke(
        matchId: Int = -1
    ): Flow<Match> = flow {
        val match = matchDataStore.getMatch(matchId)
        emit(match)
        getPeriodicTrigger(30.seconds)
            .map { applyMatchUpdates(match) }
            .collect(this@flow)
    }
}

class ApplyMatchUpdates @Inject constructor(
    private val matchDataStore: MatchDataStore
) {
    suspend operator fun invoke(
        match: Match
    ): Match =
        matchDataStore.getMatchUpdates(match.matchId)
            .let { updates ->
                match.copy(
                    events = updates.events,
                    clock = updates.clock
                )
            }

}

class GetPeriodicTrigger @Inject constructor() {
    operator fun invoke(
        triggerTime: Duration
    ): Flow<Int> {
        var count = 0
        return flow {
            delay(triggerTime)
            emit(count++)
        }
    }
}

class FailedToFetchMatchException(cause: Throwable) : RuntimeException(
    "Failed to fetch match data", cause
)