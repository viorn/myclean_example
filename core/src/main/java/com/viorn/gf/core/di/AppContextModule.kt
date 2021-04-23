package com.viorn.gf.core.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppContextModule(private val context: Context) {
    @Provides
    fun context(): Context {
        return context
    }
}