package com.codez.sportsapp.data

import com.codez.sportsapp.domain.stores.FixtureDataStore
import com.codez.sportsapp.domain.stores.MatchDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideMatchDataStore(dataStoreImpl: RemoteDataStoreImpl): MatchDataStore

    @Binds
    abstract fun provideFixtureDataStore(dataStoreImpl: RemoteDataStoreImpl): FixtureDataStore
}