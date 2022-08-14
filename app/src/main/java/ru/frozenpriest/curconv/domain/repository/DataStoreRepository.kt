package ru.frozenpriest.curconv.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.frozenpriest.curconv.domain.model.SortingMethod

interface DataStoreRepository {
    fun getSortingMethod(): Flow<SortingMethod>
    suspend fun setSortingMethod(sortingMethod: SortingMethod)
}
