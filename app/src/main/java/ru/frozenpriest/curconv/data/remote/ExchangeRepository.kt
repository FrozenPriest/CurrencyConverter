package ru.frozenpriest.curconv.data.remote

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.withContext
import ru.frozenpriest.curconv.domain.DispatcherProvider
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.Symbol
import ru.frozenpriest.curconv.domain.repository.RemoteRepository
import timber.log.Timber
import javax.inject.Inject

class ExchangeRepository @Inject constructor(
    private val api: ExchangeApi,
    private val dispatchers: DispatcherProvider
) : RemoteRepository {
    override suspend fun getSymbols() = withContext(dispatchers.io()) {
        try {
            val result = api.getSymbols()

            if (result.success) {
                Result.success(result.symbols.map { Symbol(it.key, it.value) })
            } else {
                Result.failure(IllegalStateException("Error getting symbols"))
            }
        } catch (e: CancellationException) {
            throw CancellationException(e.message)
        } catch (e: Exception) {
            Timber.e("Error getting symbols: $e")
            Result.failure(e)
        }
    }

    override suspend fun getLatest(symbol: String) = withContext(dispatchers.io()) {
        try {
            val result = api.getLatest(symbol)

            if (result.success) {
                Result.success(result.rates.map { CurrencyValue(result.base, it.key, it.value) })
            } else {
                Result.failure(IllegalStateException("Error getting symbols"))
            }
        } catch (e: CancellationException) {
            throw CancellationException(e.message)
        } catch (e: Exception) {
            Timber.e("Error getting symbols: $e")
            Result.failure(e)
        }
    }
}
