package ru.frozenpriest.curconv.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.frozenpriest.curconv.data.remote.model.LatestResponse
import ru.frozenpriest.curconv.data.remote.model.SymbolResponse

interface ExchangeApi {
    /**
     * Get all available currencies from server
     * @return response with required data
     */
    @GET("symbols")
    suspend fun getSymbols(): Response<SymbolResponse>

    /**
     * Get all latest exchange rates for desired currency
     * @param symbolKey currency code collected from "getSymbols()"
     * @return response with required data
     */
    @GET("latest")
    suspend fun getLatest(@Query("base") symbolKey: String): Response<LatestResponse>
}
