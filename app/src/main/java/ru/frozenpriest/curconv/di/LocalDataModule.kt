package ru.frozenpriest.curconv.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.frozenpriest.curconv.data.local.DataStoreRepositoryImpl
import ru.frozenpriest.curconv.data.local.ExchangeDatabase
import ru.frozenpriest.curconv.data.local.LocalRepositoryImpl
import ru.frozenpriest.curconv.data.local.dao.CurrencyDao
import ru.frozenpriest.curconv.domain.DispatcherProvider
import ru.frozenpriest.curconv.domain.DispatcherProviderImpl
import ru.frozenpriest.curconv.domain.repository.DataStoreRepository
import ru.frozenpriest.curconv.domain.repository.LocalRepository
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object LocalDataModule {
    @Provides
    fun provideDataStoreRepository(
        application: Application,
        dispatchers: DispatcherProvider
    ): DataStoreRepository =
        DataStoreRepositoryImpl(application, dispatchers)

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DispatcherProviderImpl()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext application: Context): ExchangeDatabase {
        return Room
            .databaseBuilder(application, ExchangeDatabase::class.java, "exchange_db")
            .build()
    }

    @Provides
    fun provideDao(database: ExchangeDatabase) = database.currencyDao()

    @Provides
    fun provideLocalRepository(currencyDao: CurrencyDao): LocalRepository =
        LocalRepositoryImpl(currencyDao)
}
