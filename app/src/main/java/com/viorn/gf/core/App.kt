package com.viorn.gf.core

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.viorn.gf.ScreensImpl
import com.viorn.gf.common.*
import com.viorn.gf.core.di.*

class App : Application() {
    private val mainCicerone = Cicerone.create()


    override fun onCreate() {
        super.onCreate()
        ApplicationComponent.INSTANCE = DaggerApplicationComponent
            .builder()
            .appContextModule(AppContextModule(this))
            .ciceroneModule(CiceroneModule(mainCicerone, ScreensImpl())).build()

        mainCicerone.router.newRootScreen(SCREENS.main())

    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}