package com.codez.sportsapp.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideJsonSerializer() : Json {
        return Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.HEADERS
                    }
            )
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        jsonSerializer: Json
    ): Retrofit {
        return Retrofit.Builder()
            .callFactory { okHttpClient.newCall(it) }
            .baseUrl("https://pyates-twocircles.github.io/two-circles-tech-test/")
            .addConverterFactory(
                jsonSerializer.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideServiceAPI(
        retrofit: Retrofit
    ): ServiceAPI {
        return retrofit.create(ServiceAPI::class.java)
    }
}