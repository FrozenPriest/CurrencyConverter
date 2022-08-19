package ru.frozenpriest.curconv.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.frozenpriest.curconv.domain.model.SortingMethod

/**
 * Repository for interaction with datastore preferences
 */
interface DataStoreRepository {
    /**
     * Get sorting method flow
     * @return flow with sorting method
     */
    fun getSortingMethod(): Flow<SortingMethod>

    /**
     * Sets sorting method
     * @param sortingMethod new sorting method
     */
    suspend fun setSortingMethod(sortingMethod: SortingMethod)
}
