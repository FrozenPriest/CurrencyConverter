package ru.frozenpriest.curconv.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.frozenpriest.curconv.data.local.dao.CurrencyDao
import ru.frozenpriest.curconv.data.local.entity.CurrencyValueEntity
import ru.frozenpriest.curconv.data.local.entity.SymbolEntity

@Database(
    entities = [
        SymbolEntity::class,
        CurrencyValueEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class ExchangeDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}
