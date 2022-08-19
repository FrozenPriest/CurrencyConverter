package ru.frozenpriest.curconv.data.local

import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.frozenpriest.curconv.data.local.dao.CurrencyDao
import ru.frozenpriest.curconv.data.local.dao.SymbolDao
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.SortingMethod
import ru.frozenpriest.curconv.domain.model.Symbol
import ru.frozenpriest.curconv.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val symbolDao: SymbolDao
) : LocalRepository {
    override fun getSymbols() = symbolDao.getSymbols()
        .distinctUntilChanged()
        .map { list ->
            list.map { symbolEntity -> symbolEntity.toSymbol() }
        }

    override suspend fun saveSymbols(symbols: List<Symbol>) {
        symbolDao.upsert(symbols.map { it.toEntity() })
    }

    override fun getValues(
        symbol: Symbol,
        sortingMethod: SortingMethod,
        favoriteOnly: Boolean
    ) = if (favoriteOnly) {
        currencyDao.getFavoriteValues(
            symbol.code,
            sortingMethod.type.id,
            sortingMethod.isAscending,
        )
    } else {
        currencyDao.getCurrencyValues(
            symbol.code,
            sortingMethod.type.id,
            sortingMethod.isAscending,
        )
    }.distinctUntilChanged().map { list ->
        list.map { valueEntity -> valueEntity.toValue() }
    }

    /**
     * Used to save values from internet
     * Ignores 'isFavorite' field
     */
    override suspend fun saveValues(currencyValues: List<CurrencyValue>) {
        currencyDao.upsert(currencyValues.map { it.toPartialEntity() })
    }

    override suspend fun updateValue(currencyValue: CurrencyValue) {
        currencyDao.addCurrencyValue(currencyValue.toEntity())
    }
}
