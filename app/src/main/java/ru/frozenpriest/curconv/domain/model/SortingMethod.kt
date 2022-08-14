package ru.frozenpriest.curconv.domain.model

data class SortingMethod(
    val type: SortingType,
    val isAscending: Boolean
)

enum class SortingType {
    Alphabet, ByValue
}
