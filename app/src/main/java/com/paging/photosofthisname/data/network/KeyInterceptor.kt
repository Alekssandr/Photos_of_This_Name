package com.paging.photosofthisname.data.network

import com.paging.photosofthisname.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


const val API_KEY = "api_key"
const val FORMAT_JSON = "format"
const val NO_JSON_CALLBACK = "nojsoncallback"

object KeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.FLICKR_API_KEY)
            .addQueryParameter(FORMAT_JSON, "json")
            .addQueryParameter(NO_JSON_CALLBACK, "1")
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}
