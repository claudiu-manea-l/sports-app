package com.codez.sportsapp.domain.stores

import com.codez.sportsapp.domain.Match
import com.codez.sportsapp.domain.MatchInfo
import com.codez.sportsapp.domain.MatchUpdates
import com.codez.sportsapp.domain.Team

interface MatchDataStore {
    suspend fun getMatch(id:Int) : Match
    suspend fun getMatchUpdates(id:Int) : MatchUpdates
}
