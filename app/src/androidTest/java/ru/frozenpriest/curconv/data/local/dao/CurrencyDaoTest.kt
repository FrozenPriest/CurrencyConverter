package ru.frozenpriest.curconv.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.frozenpriest.curconv.data.local.ExchangeDatabase
import ru.frozenpriest.curconv.data.local.entity.CurrencyValueEntity
import ru.frozenpriest.curconv.data.local.entity.CurrencyValuePartial
import ru.frozenpriest.curconv.data.local.entity.SymbolEntity
import ru.frozenpriest.curconv.domain.model.SortingType
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class CurrencyDaoTest {
    private lateinit var currencyDao: CurrencyDao
    private lateinit var db: ExchangeDatabase

    private val symbolOne = SymbolEntity("USD", "USD")
    private val symbolTwo = SymbolEntity("USD2", "USD2")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ExchangeDatabase::class.java).build()
        currencyDao = db.currencyDao()

        runBlocking {
            db.symbolDao().apply {
                upsert(listOf(symbolOne, symbolTwo))
            }
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun addNewSuccessfully() = runTest {
        val currency = CurrencyValuePartial(symbolOne.code, symbolTwo.code, 2.0)

        currencyDao.getCurrencyValues(symbolOne.code, SortingType.Alphabet.id, true).test {
            assertEquals(awaitItem().size, 0)
            currencyDao.upsert(listOf(currency))
            awaitItem().apply {
                assertEquals(size, 1)
                assertEquals(first(), currency.toFull())
            }
        }
    }

    @Test
    fun addNewUpdateSuccessfully() = runTest {
        val currency = CurrencyValueEntity(symbolOne.code, symbolTwo.code, 2.0, true)

        currencyDao.getCurrencyValues(symbolOne.code, SortingType.Alphabet.id, true).test {
            assertEquals(awaitItem().size, 0)
            currencyDao.addCurrencyValue(currency)
            awaitItem().apply {
                assertEquals(size, 1)
                assertEquals(first(), currency)
            }
            currencyDao.upsert(listOf(currency.toPartial()))
            awaitItem().apply {
                assertEquals(size, 1)
                assertEquals(first(), currency)
            }
        }
    }
}

private fun CurrencyValuePartial.toFull() = CurrencyValueEntity(from, to, value, false)
private fun CurrencyValueEntity.toPartial() = CurrencyValuePartial(from, to, value)
