package com.example.gamervault.data.remote

import com.example.gamervault.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val originalUrl = original.url
        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("key", BuildConfig.API_KEY)
            .build()


        val request = original.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(request)
    }
}