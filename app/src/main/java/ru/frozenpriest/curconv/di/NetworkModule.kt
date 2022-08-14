package ru.frozenpriest.curconv.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.frozenpriest.curconv.BuildConfig
import ru.frozenpriest.curconv.data.remote.ExchangeApi
import ru.frozenpriest.curconv.data.remote.ExchangeRepository
import ru.frozenpriest.curconv.data.remote.TokenInterceptor
import ru.frozenpriest.curconv.domain.DispatcherProvider
import ru.frozenpriest.curconv.domain.repository.RemoteRepository
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object NetworkModule {
    val Context.dataStore by preferencesDataStore("botsad_datastore")

    @Provides
    fun provideNetRepository(api: ExchangeApi, dispatchers: DispatcherProvider): RemoteRepository =
        ExchangeRepository(api, dispatchers)

    @Provides
    fun provideApi(retrofit: Retrofit): ExchangeApi = retrofit.create(ExchangeApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, @BaseUrl baseUrl: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(baseUrl)
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(TokenInterceptor())
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
        }.build()
    }

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = "https://api.apilayer.com/exchangerates_data/"
}
