package ru.frozenpriest.curconv.ui.screen.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.frozenpriest.curconv.domain.model.SortingType
import ru.frozenpriest.curconv.domain.repository.DataStoreRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    val state = dataStoreRepository.getSortingMethod()

    fun toggle(alphabet: SortingType, ascending: Boolean) {
        TODO("Not yet implemented")
    }
}
