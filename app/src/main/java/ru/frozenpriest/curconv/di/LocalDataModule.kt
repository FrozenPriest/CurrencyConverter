package ru.frozenpriest.curconv.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.frozenpriest.curconv.data.local.DataStoreRepositoryImpl
import ru.frozenpriest.curconv.domain.DispatcherProvider
import ru.frozenpriest.curconv.domain.DispatcherProviderImpl
import ru.frozenpriest.curconv.domain.repository.DataStoreRepository

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
}
