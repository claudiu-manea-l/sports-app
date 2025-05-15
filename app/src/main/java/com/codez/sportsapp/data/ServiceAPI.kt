package com.codez.sportsapp.data

import retrofit2.http.GET

interface ServiceAPI {

    @GET("match.json")
    suspend fun getMatchData(): MatchesResponse

    @GET("fixtures.json")
    suspend fun getFixtures(): List<FixtureDTO>

}

