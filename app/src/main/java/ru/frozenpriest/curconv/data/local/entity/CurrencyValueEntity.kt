package ru.frozenpriest.curconv.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "currency_values",
    primaryKeys = ["from", "to"]
)
data class CurrencyValueEntity(
    @ColumnInfo(name = "from")
    val from: String,
    @ColumnInfo(name = "to")
    val to: String,
    @ColumnInfo(name = "value")
    val value: Double,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean
)
