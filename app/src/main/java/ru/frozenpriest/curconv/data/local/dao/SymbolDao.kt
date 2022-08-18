package ru.frozenpriest.curconv.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.frozenpriest.curconv.data.local.entity.SymbolEntity

@Dao
abstract class SymbolDao : BaseDao<SymbolEntity>() {
    @Query("select * from symbols")
    abstract fun getSymbols(): Flow<List<SymbolEntity>>
}
