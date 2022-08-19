package ru.frozenpriest.curconv.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.frozenpriest.curconv.data.local.ExchangeDatabase
import ru.frozenpriest.curconv.data.local.entity.SymbolEntity
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class SymbolDaoTest {
    private lateinit var symbolDao: SymbolDao
    private lateinit var db: ExchangeDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ExchangeDatabase::class.java).build()
        symbolDao = db.symbolDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun addNewCurrencySuccessfully() = runTest {
        val currency = SymbolEntity("USD", "TEST")
        symbolDao.getSymbols().test {
            assertEquals(awaitItem().size, 0)
            symbolDao.insert(listOf(currency))
            awaitItem().apply {
                assertEquals(size, 1)
                assertEquals(first(), currency)
            }
        }
    }

    @Test
    fun addTwoNewCurrencySuccessfully() = runTest {
        val currency = SymbolEntity("USD", "TEST")
        val currency2 = SymbolEntity("USD2", "TEST2")

        symbolDao.getSymbols().test {
            assertEquals(awaitItem().size, 0)
            symbolDao.upsert(listOf(currency, currency2))
            awaitItem().apply {
                assertEquals(size, 2)
                assert(contains(currency) && contains(currency2))
            }
        }
    }

    @Test
    fun updateExistingCurrencySuccessfully() = runTest {
        val currency = SymbolEntity("USD", "TEST")
        val currency2 = SymbolEntity("USD", "TEST2")

        symbolDao.getSymbols().test {
            assertEquals(awaitItem().size, 0)
            symbolDao.upsert(listOf(currency))
            awaitItem().apply {
                assertEquals(size, 1)
                assert(contains(currency))
            }
            symbolDao.upsert(listOf(currency2))
            awaitItem().apply {
                assertEquals(size, 1)
                assert(contains(currency2))
            }
        }
    }
}
