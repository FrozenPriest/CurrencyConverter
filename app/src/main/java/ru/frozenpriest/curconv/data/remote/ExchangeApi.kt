package ru.frozenpriest.curconv.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.frozenpriest.curconv.data.remote.model.LatestResponse
import ru.frozenpriest.curconv.data.remote.model.SymbolResponse

interface ExchangeApi {
    @GET("symbols")
    suspend fun getSymbols(): SymbolResponse

    @GET("latest")
    suspend fun getLatest(@Query("base") symbolKey: String): LatestResponse
}
