package com.dreamsoftware.tvnexa.data.network.interceptors

import com.dreamsoftware.tvnexa.utils.ISessionAware
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sessionAware: ISessionAware,
): Interceptor {

    private companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest
            .newBuilder()
            .addHeader(AUTHORIZATION_HEADER, "Bearer ${sessionAware.session?.token}")
            .build()
        return chain.proceed(newRequest)
    }
}
