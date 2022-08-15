package ru.frozenpriest.curconv.domain.model

import androidx.compose.runtime.Stable

@Stable
data class Symbol(
    val code: String,
    val name: String
)
