package ru.frozenpriest.curconv.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.frozenpriest.curconv.domain.model.Symbol
import ru.frozenpriest.curconv.domain.repository.LocalRepository
import ru.frozenpriest.curconv.domain.repository.RemoteRepository
import javax.inject.Inject

interface UpdateSymbolsUseCase {
    /**
     * Flow of currencies to be shown
     */
    val symbolsFlow: Flow<List<Symbol>>

    /**
     * Function for requesting update from source
     */
    suspend fun update(onError: suspend (Throwable) -> Unit)
}

class UpdateSymbolsUseCaseImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : UpdateSymbolsUseCase {
    override val symbolsFlow = localRepository.getSymbols()

    override suspend fun update(onError: suspend (Throwable) -> Unit) {
        remoteRepository.getSymbols().fold(
            onSuccess = { symbols ->
                localRepository.saveSymbols(symbols)
            },
            onFailure = {
                onError(it)
            }
        )
    }
}
