package ru.frozenpriest.curconv.data.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LatestResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Map<String, Double>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: Long
)
