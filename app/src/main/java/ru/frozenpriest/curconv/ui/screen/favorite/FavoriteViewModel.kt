package ru.frozenpriest.curconv.ui.screen.favorite

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.Symbol
import ru.frozenpriest.curconv.domain.usecase.UpdateSymbolsUseCase
import ru.frozenpriest.curconv.domain.usecase.UpdateValuesUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val updateSymbolsUseCase: UpdateSymbolsUseCase,
    private val updateValuesUseCase: UpdateValuesUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading get() = _isLoading.asStateFlow()

    val symbols = updateSymbolsUseCase.symbolsFlow
    val selectedSymbol = MutableStateFlow<Symbol?>(null)

    @OptIn(FlowPreview::class)
    val currencyValues = selectedSymbol.flatMapMerge { symbolNullable ->
        refreshValues()
        symbolNullable?.let { symbol -> updateValuesUseCase.getValues(symbol, true) }
            ?: emptyFlow()
    }

    init {
        Timber.d("Reiniting viewmodel $this")
        viewModelScope.launch {
            symbols.collectLatest { symbols ->
                if (selectedSymbol.value == null) selectedSymbol.update { symbols.firstOrNull() }
            }
        }
    }

    fun updateSymbols() {
        viewModelScope.launch {
            updateSymbolsUseCase.update {}
        }
    }

    fun refreshValues() {
        viewModelScope.launch {
            val stateValue = selectedSymbol.value
            _isLoading.update { true }
            stateValue?.let {
                updateValuesUseCase.update(it) {}
            }
            _isLoading.update { false }
        }
    }

    fun favoriteClicked(value: CurrencyValue) {
        viewModelScope.launch {
            updateValuesUseCase.updateLocalValue(
                value.copy(
                    isFavorite = !(value.isFavorite ?: false)
                )
            )
        }
    }

    fun setSymbol(symbol: Symbol) {
        selectedSymbol.update { symbol }
        refreshValues()
    }
}

sealed class Event(@StringRes val message: Int?) {
    class Error(@StringRes errorMessage: Int) : Event(errorMessage)
}
