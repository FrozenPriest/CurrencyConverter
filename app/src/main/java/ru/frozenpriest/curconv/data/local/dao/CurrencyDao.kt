package ru.frozenpriest.curconv.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.frozenpriest.curconv.data.local.entity.CurrencyValueEntity
import ru.frozenpriest.curconv.data.local.entity.SymbolEntity

@Dao
interface CurrencyDao {
    @Query("select * from symbols")
    fun getSymbols(): Flow<List<SymbolEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSymbols(items: List<SymbolEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrencyValues(items: List<CurrencyValueEntity>)

    @Query(
        """
        SELECT * FROM currency_values 
        WHERE `from` LIKE :base
        ORDER BY 
        CASE WHEN :order = 1 AND :ascending = 1 THEN `to` END ASC, 
        CASE WHEN :order = 1 AND :ascending = 0 THEN `to` END DESC,
        CASE WHEN :order = 2 AND :ascending = 1 THEN value END ASC, 
        CASE WHEN :order = 2 AND :ascending = 0 THEN value END DESC
        """
    )
    fun getCurrencyValues(
        base: String,
        order: Int,
        ascending: Boolean,
    ): Flow<List<CurrencyValueEntity>>

    @Query(
        """
        SELECT * FROM currency_values 
        WHERE `from` LIKE :base AND isFavorite = 1
        ORDER BY 
        CASE WHEN :order = 1 AND :ascending = 1 THEN `to` END ASC, 
        CASE WHEN :order = 1 AND :ascending = 0 THEN `to` END DESC,
        CASE WHEN :order = 2 AND :ascending = 1 THEN value END ASC, 
        CASE WHEN :order = 2 AND :ascending = 0 THEN value END DESC
        """
    )
    fun getFavoriteValues(
        base: String,
        order: Int,
        ascending: Boolean,
    ): Flow<List<CurrencyValueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCurrencyValue(currencyValue: CurrencyValueEntity)
}
