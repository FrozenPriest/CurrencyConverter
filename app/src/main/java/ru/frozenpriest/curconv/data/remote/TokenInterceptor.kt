package ru.frozenpriest.curconv.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import ru.frozenpriest.curconv.BuildConfig

class TokenInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("apikey", BuildConfig.API_KEY)
            .build()

        return chain.proceed(request)
    }
}
