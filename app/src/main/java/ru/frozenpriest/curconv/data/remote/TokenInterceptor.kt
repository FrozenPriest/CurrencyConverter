package ru.frozenpriest.curconv.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import ru.frozenpriest.curconv.domain.repository.DataStoreRepository

class TokenInterceptor(dataStoreRepository: DataStoreRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }
}
