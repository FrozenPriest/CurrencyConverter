package ru.frozenpriest.curconv.domain.model

import androidx.compose.runtime.Stable

/**
 * Currency exchange rate model
 * @param from initial currency code
 * @param to exchanged currency code
 * @param value exchange rate value
 * @param isFavorite currency is in favorites
 */
@Stable
data class CurrencyValue(
    val from: String,
    val to: String,
    val value: Double,
    val isFavorite: Boolean? = null
)
