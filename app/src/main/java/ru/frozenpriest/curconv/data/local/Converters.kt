/**
 * File containing data <-> domain model mappers
 */
package ru.frozenpriest.curconv.data.local

import ru.frozenpriest.curconv.data.local.entity.CurrencyValueEntity
import ru.frozenpriest.curconv.data.local.entity.CurrencyValuePartial
import ru.frozenpriest.curconv.data.local.entity.SymbolEntity
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.Symbol

fun SymbolEntity.toSymbol() = Symbol(code, name)
fun Symbol.toEntity() = SymbolEntity(code, name)

fun CurrencyValueEntity.toValue() = CurrencyValue(from, to, value, isFavorite)
fun CurrencyValue.toEntity() = CurrencyValueEntity(from, to, value, isFavorite ?: false)
fun CurrencyValue.toPartialEntity() = CurrencyValuePartial(from, to, value)
