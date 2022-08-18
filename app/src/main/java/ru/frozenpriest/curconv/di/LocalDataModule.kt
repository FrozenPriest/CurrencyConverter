package ru.frozenpriest.curconv.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.frozenpriest.curconv.data.local.DataStoreRepositoryImpl
import ru.frozenpriest.curconv.data.local.ExchangeDatabase
import ru.frozenpriest.curconv.data.local.LocalRepositoryImpl
import ru.frozenpriest.curconv.domain.DispatcherProvider
import ru.frozenpriest.curconv.domain.DispatcherProviderImpl
import ru.frozenpriest.curconv.domain.repository.DataStoreRepository
import ru.frozenpriest.curconv.domain.repository.LocalRepository
import ru.frozenpriest.curconv.domain.usecase.UpdateSymbolsUseCase
import ru.frozenpriest.curconv.domain.usecase.UpdateSymbolsUseCaseImpl
import ru.frozenpriest.curconv.domain.usecase.UpdateValuesUseCase
import ru.frozenpriest.curconv.domain.usecase.UpdateValuesUseCaseImpl
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class LocalDataModule {
    companion object {
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
        fun provideCurrencyDao(database: ExchangeDatabase) = database.currencyDao()

        @Provides
        fun provideSymbolDao(database: ExchangeDatabase) = database.symbolDao()
    }

    @Binds
    abstract fun provideLocalRepository(impl: LocalRepositoryImpl): LocalRepository

    @Binds
    abstract fun bindUpdateSymbols(updateSymbolsUseCase: UpdateSymbolsUseCaseImpl): UpdateSymbolsUseCase

    @Binds
    abstract fun bindUpdateValues(updateValuesUseCaseImpl: UpdateValuesUseCaseImpl): UpdateValuesUseCase
}
