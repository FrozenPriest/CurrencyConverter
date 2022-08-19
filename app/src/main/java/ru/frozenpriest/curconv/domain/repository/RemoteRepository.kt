package ru.frozenpriest.curconv.domain.repository

import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.Symbol

/**
 * Remote data repository
 */
interface RemoteRepository {
    /**
     * Get all available currencies
     * @returns list of currencies or error
     */
    suspend fun getSymbols(): Result<List<Symbol>>

    /**
     * Get latest exchange rates for currency
     * @param code currency code
     */
    suspend fun getLatest(code: String): Result<List<CurrencyValue>>
}
