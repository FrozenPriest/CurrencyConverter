package ru.frozenpriest.curconv.domain.repository

import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.Symbol

interface RemoteRepository {
    suspend fun getSymbols(): List<Symbol>
    suspend fun getLatest(symbol: Symbol): List<CurrencyValue>
}
