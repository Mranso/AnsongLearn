package com.anso.learn.baselibrary.net

import com.anso.learn.baselibrary.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit 服务构建
 * author：Yuantian
 * time：2023/3/25 23:52
 */
class RetrofitClient {

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(createOkHttpClient())
            .baseUrl(Const.WanAndroid_Base_Api)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return createRetrofit().create(service)
    }

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitClient()
        }
    }
}