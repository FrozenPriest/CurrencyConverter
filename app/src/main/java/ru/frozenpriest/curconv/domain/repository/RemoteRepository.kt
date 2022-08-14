package ru.frozenpriest.curconv.domain.repository

import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.Symbol

interface RemoteRepository {
    suspend fun getSymbols(): Result<List<Symbol>>
    suspend fun getLatest(symbol: String): Result<List<CurrencyValue>>
}
