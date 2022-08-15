package ru.frozenpriest.curconv.domain.model

import androidx.compose.runtime.Stable

@Stable
data class SortingMethod(
    val type: SortingType,
    val isAscending: Boolean
)

enum class SortingType(val item: Int) {
    Alphabet(1), ByValue(2)
}
