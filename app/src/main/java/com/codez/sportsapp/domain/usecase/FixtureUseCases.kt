package com.codez.sportsapp.domain.usecase

import com.codez.sportsapp.data.ServiceAPI
import com.codez.sportsapp.domain.Fixture
import com.codez.sportsapp.domain.stores.FixtureDataStore
import javax.inject.Inject

class GetFixtures @Inject constructor(
    private val fixtureDataStore: FixtureDataStore
) {
    suspend operator fun invoke() : List<Fixture> {
        return fixtureDataStore.getFixtures()
    }
}