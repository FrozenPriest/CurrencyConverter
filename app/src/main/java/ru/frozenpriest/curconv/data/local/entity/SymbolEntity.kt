package ru.frozenpriest.curconv.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity containing currency code and name
 * @param code currency code
 * @param name full currency name
 */
@Entity(tableName = "symbols")
data class SymbolEntity(
    @PrimaryKey
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "name")
    val name: String
)
