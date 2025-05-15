package com.codez.sportsapp.domain.stores

import com.codez.sportsapp.domain.Fixture
import com.codez.sportsapp.domain.Match
import com.codez.sportsapp.domain.MatchUpdates

interface MatchDataStore {
    suspend fun getMatch(id:Int) : Match
    suspend fun getMatchUpdates(id:Int) : MatchUpdates
}

interface FixtureDataStore {
    suspend fun getFixtures() : List<Fixture>
}