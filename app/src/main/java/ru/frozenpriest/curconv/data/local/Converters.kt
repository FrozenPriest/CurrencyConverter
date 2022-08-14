package ru.frozenpriest.curconv.data.local

import ru.frozenpriest.curconv.data.local.entity.CurrencyValueEntity
import ru.frozenpriest.curconv.data.local.entity.SymbolEntity
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.Symbol

fun SymbolEntity.toSymbol(): Symbol = Symbol(code, name)
fun CurrencyValueEntity.toValue(): CurrencyValue = CurrencyValue(from, to, value, isFavorite)
