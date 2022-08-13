package ru.frozenpriest.curconv.domain.model

data class CurrencyValue(
    val id: Int,
    val to: String,
    val value: Double,
    val isFavorite: Boolean
)
