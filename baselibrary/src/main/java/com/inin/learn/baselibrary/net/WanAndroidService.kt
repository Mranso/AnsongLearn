package com.inin.learn.baselibrary.net

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * WanAndroid API 接口
 * author：Yuantian
 * time：2023/3/25 23:37
 */
interface WanAndroidService {

    /**
     * WanAndroid 登录
     * */
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String, @Field("password") password: String)
            : WanAndroidBaseEntity<Any>

}