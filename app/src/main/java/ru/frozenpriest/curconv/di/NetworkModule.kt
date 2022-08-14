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
import ru.frozenpriest.curconv.data.remote.TokenInterceptor
import ru.frozenpriest.curconv.domain.repository.DataStoreRepository
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object NetworkModule {
    val Context.dataStore by preferencesDataStore("botsad_datastore")

    @Singleton
    @Provides
    fun provideOkHttpClient(dataStoreRepository: DataStoreRepository): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(TokenInterceptor(dataStoreRepository))
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, @BaseUrl baseUrl: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(baseUrl)
            .build()

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = "https://api.apilayer.com/exchangerates_data/"
}
