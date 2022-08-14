package ru.frozenpriest.curconv.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.frozenpriest.curconv.domain.model.SortingMethod
import ru.frozenpriest.curconv.domain.model.SortingType
import ru.frozenpriest.curconv.domain.repository.DataStoreRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    val state = dataStoreRepository.getSortingMethod()

    fun toggle(type: SortingType, ascending: Boolean) {
        viewModelScope.launch {
            dataStoreRepository.setSortingMethod(SortingMethod(type, ascending))
        }
    }
}
