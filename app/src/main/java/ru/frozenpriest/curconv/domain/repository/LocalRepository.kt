package ru.frozenpriest.curconv.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.SortingMethod
import ru.frozenpriest.curconv.domain.model.Symbol

/**
 * Repository for interaction with local database
 */
interface LocalRepository {
    /**
     * @return flow of currencies from database
     */
    fun getSymbols(): Flow<List<Symbol>>

    /**
     * Adds new currencies to database, existing items are updated
     * @param symbols list of currencies
     */
    suspend fun saveSymbols(symbols: List<Symbol>)

    /**
     * @param symbol selected 'from' currency
     * @param sortingMethod exchange rates sorting method
     * @param favoriteOnly return only items marked as favorite
     * @returns flow of currency values from database
     */
    fun getValues(
        symbol: Symbol,
        sortingMethod: SortingMethod,
        favoriteOnly: Boolean
    ): Flow<List<CurrencyValue>>

    /**
     * Adds new exchange rates to database, existing items are updated
     * Ignores @param isFavorite
     * @param currencyValues list of exchange rates
     */
    suspend fun saveValues(currencyValues: List<CurrencyValue>)

    /**
     * Updates exchange rate in database
     * @param currencyValue exchange rate
     */
    suspend fun updateValue(currencyValue: CurrencyValue)
}
