package ru.frozenpriest.curconv.data.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SymbolResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("symbols")
    val symbols: Map<String, String>,
)
