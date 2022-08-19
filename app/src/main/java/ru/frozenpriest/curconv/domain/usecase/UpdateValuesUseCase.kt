package ru.frozenpriest.curconv.domain.usecase

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.Symbol
import ru.frozenpriest.curconv.domain.repository.DataStoreRepository
import ru.frozenpriest.curconv.domain.repository.LocalRepository
import ru.frozenpriest.curconv.domain.repository.RemoteRepository
import javax.inject.Inject

interface UpdateValuesUseCase {
    /**
     * Flow of exchange rates for selected currency
     * @param symbol selected currency
     * @param favoriteOnly flag for showing only favorite currencies
     */
    fun getValues(symbol: Symbol, favoriteOnly: Boolean): Flow<List<CurrencyValue>>

    /**
     * Request for updating local data from remote source
     * @param symbol currency
     * @param onError callback when error is thrown
     */
    suspend fun update(symbol: Symbol, onError: suspend (Throwable) -> Unit)

    /**
     * Update exchange rate in local database
     * @param value exchange rate to be updated
     */
    suspend fun updateLocalValue(value: CurrencyValue)
}

class UpdateValuesUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val dataStoreRepository: DataStoreRepository
) : UpdateValuesUseCase {
    @OptIn(FlowPreview::class)
    override fun getValues(symbol: Symbol, favoriteOnly: Boolean) =
        dataStoreRepository.getSortingMethod().flatMapMerge {
            localRepository.getValues(symbol, it, favoriteOnly)
        }

    override suspend fun update(symbol: Symbol, onError: suspend (Throwable) -> Unit) {
        remoteRepository.getLatest(symbol.code).fold(
            onSuccess = { currencyValues ->
                localRepository.saveValues(currencyValues)
            },
            onFailure = {
                onError(it)
            }
        )
    }

    override suspend fun updateLocalValue(value: CurrencyValue) {
        localRepository.updateValue(value)
    }
}
