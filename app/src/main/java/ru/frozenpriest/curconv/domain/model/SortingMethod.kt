package ru.frozenpriest.curconv.domain.model

import androidx.compose.runtime.Stable

/**
 * Sorting method model
 * Contains:
 * @param type sorting type
 * @param isAscending true when using ascending order
 */
@Stable
data class SortingMethod(
    val type: SortingType,
    val isAscending: Boolean
)

/**
 * Enum containing allowed sorting types
 */
enum class SortingType(val id: Int) {
    /**
     * Sorting by name
     */
    Alphabet(1),

    /**
     * Sorting by exchange rate value
     */
    ByValue(2)
}
