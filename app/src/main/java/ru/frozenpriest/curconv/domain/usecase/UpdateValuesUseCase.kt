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
    fun getValues(symbol: Symbol, favoriteOnly: Boolean): Flow<List<CurrencyValue>>
    suspend fun update(symbol: Symbol, onError: (Throwable) -> Unit)
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

    override suspend fun update(symbol: Symbol, onError: (Throwable) -> Unit) {
        remoteRepository.getLatest(symbol.code).fold(
            onSuccess = { currencyValues ->
                localRepository.saveValues(currencyValues)
            },
            onFailure = {
                onError(it)
            }
        )
    }
}
