package ru.frozenpriest.curconv.domain.model

data class CurrencyValue(
    val from: String,
    val to: String,
    val value: Double,
    val isFavorite: Boolean
)
