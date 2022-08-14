package ru.frozenpriest.curconv.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.SortingMethod
import ru.frozenpriest.curconv.domain.model.Symbol

interface LocalRepository {
    fun getSymbols(): Flow<List<Symbol>>
    suspend fun saveSymbols(symbols: List<Symbol>)

    fun getValues(symbol: Symbol, sortingMethod: SortingMethod, favoriteOnly: Boolean): Flow<List<CurrencyValue>>
    suspend fun saveValues(currencyValues: List<CurrencyValue>)
    suspend fun updateValue(currencyValue: CurrencyValue)
}
