package ru.frozenpriest.curconv.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Entity containing currency exchange rate
 * @param from initial currency code
 * @param to exchanged currency code
 * @param value exchange rate value
 * @param isFavorite currency is in favorites
 */
@Entity(
    tableName = "currency_values",
    primaryKeys = ["from", "to"],
    foreignKeys = [
        ForeignKey(
            entity = SymbolEntity::class,
            parentColumns = ["code"],
            childColumns = ["from"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SymbolEntity::class,
            parentColumns = ["code"],
            childColumns = ["to"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [Index("from"), Index("to")]
)
data class CurrencyValueEntity(
    @ColumnInfo(name = "from")
    val from: String,
    @ColumnInfo(name = "to")
    val to: String,
    @ColumnInfo(name = "value")
    val value: Double,
    @ColumnInfo(name = "isFavorite", defaultValue = "0")
    val isFavorite: Boolean
)

/**
 * Partial currency_value entity without isFavorite
 */
@Entity
data class CurrencyValuePartial(
    @ColumnInfo(name = "from")
    val from: String,
    @ColumnInfo(name = "to")
    val to: String,
    @ColumnInfo(name = "value")
    val value: Double,
)
