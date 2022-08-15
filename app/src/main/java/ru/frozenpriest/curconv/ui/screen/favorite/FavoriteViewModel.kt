package ru.frozenpriest.curconv.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.repository.LocalRepository
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _state = MutableStateFlow(FavoriteState(false, emptyList()))
    val state get() = _state.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(2000)
            _state.update {
                it.copy(
                    currencies = listOf(
                        CurrencyValue("f", "USD", 12.43, true),
                        CurrencyValue("f", "TEST", 12555.43, false)
                    ),
                    isLoading = false
                )
            }
        }
    }

    fun favoriteClicked(value: CurrencyValue) = Unit
    data class FavoriteState(
        val isLoading: Boolean,
        val currencies: List<CurrencyValue>
    )
}
