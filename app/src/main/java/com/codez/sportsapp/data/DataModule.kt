package com.codez.sportsapp.data

import com.codez.sportsapp.domain.stores.MatchDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideMatchDataStore(dataStoreImpl: RemoteDataStoreImpl): MatchDataStore
}