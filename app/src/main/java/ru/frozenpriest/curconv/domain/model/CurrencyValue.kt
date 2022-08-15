package ru.frozenpriest.curconv.domain.model

import androidx.compose.runtime.Stable

@Stable
data class CurrencyValue(
    val from: String,
    val to: String,
    val value: Double,
    val isFavorite: Boolean? = null
)
