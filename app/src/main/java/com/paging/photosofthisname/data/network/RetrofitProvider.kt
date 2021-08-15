package com.paging.photosofthisname.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder().addInterceptor(KeyInterceptor).build()

/**
 * Build Retrofit
 */
inline fun <reified T> provideRetrofit(url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(createOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}