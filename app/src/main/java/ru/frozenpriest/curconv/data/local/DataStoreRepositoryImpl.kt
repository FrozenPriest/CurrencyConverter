package ru.frozenpriest.curconv.data.local

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.frozenpriest.curconv.di.NetworkModule.dataStore
import ru.frozenpriest.curconv.domain.DispatcherProvider
import ru.frozenpriest.curconv.domain.model.SortingMethod
import ru.frozenpriest.curconv.domain.model.SortingType
import ru.frozenpriest.curconv.domain.repository.DataStoreRepository
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    application: Application,
    private val dispatchers: DispatcherProvider
) : DataStoreRepository {
    private val dataStore = application.applicationContext.dataStore

    private companion object {
        val sortingTypeKey = stringPreferencesKey("sorting_type_key")
        val isAscendingKey = booleanPreferencesKey("is_ascending_key")
    }

    override fun getSortingMethod(): Flow<SortingMethod> = dataStore.data.map {
        val type = SortingType.valueOf(it[sortingTypeKey] ?: SortingType.Alphabet.name)
        val isAscending = it[isAscendingKey] ?: true
        SortingMethod(type, isAscending)
    }

    override suspend fun setSortingMethod(sortingMethod: SortingMethod): Unit =
        withContext(dispatchers.io()) {
            dataStore.edit {
                it[sortingTypeKey] = sortingMethod.type.name
                it[isAscendingKey] = sortingMethod.isAscending
            }
        }
}
