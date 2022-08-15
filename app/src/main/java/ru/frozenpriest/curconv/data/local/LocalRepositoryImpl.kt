package ru.frozenpriest.curconv.data.local

import kotlinx.coroutines.flow.map
import ru.frozenpriest.curconv.data.local.dao.CurrencyDao
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.SortingMethod
import ru.frozenpriest.curconv.domain.model.Symbol
import ru.frozenpriest.curconv.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val currencyDao: CurrencyDao
) : LocalRepository {
    override fun getSymbols() = currencyDao.getSymbols().map { list ->
        list.map { symbolEntity -> symbolEntity.toSymbol() }
    }

    override suspend fun saveSymbols(symbols: List<Symbol>) {
        currencyDao.addSymbols(symbols.map { it.toEntity() })
    }

    override fun getValues(
        symbol: Symbol,
        sortingMethod: SortingMethod,
        favoriteOnly: Boolean
    ) = if (favoriteOnly) {
        currencyDao.getFavoriteValues(
            symbol.code,
            sortingMethod.type.item,
            sortingMethod.isAscending,
        )
    } else {
        currencyDao.getCurrencyValues(
            symbol.code,
            sortingMethod.type.item,
            sortingMethod.isAscending,
        )
    }.map { list ->
        list.map { valueEntity -> valueEntity.toValue() }
    }

    override suspend fun saveValues(currencyValues: List<CurrencyValue>) {
        currencyDao.addCurrencyValues(currencyValues.map { it.toPartialEntity() })
    }

    override suspend fun updateValue(currencyValue: CurrencyValue) {
        currencyDao.addCurrencyValue(currencyValue.toEntity())
    }
}