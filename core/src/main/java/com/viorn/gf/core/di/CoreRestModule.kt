package com.viorn.gf.core.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CoreRestModule {
    @com.viorn.gf.core.di.CoreScope
    @Provides
    fun retrofit(okhttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okhttp)
            .baseUrl("http://vps91319.hostfx.ru")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @com.viorn.gf.core.di.CoreScope
    @Provides
    fun okhttp(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}