package com.viorn.gf.core.di

import com.github.terrakok.cicerone.BaseRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class CiceroneModule(private val mainCicerone: Cicerone<out BaseRouter>, private val screens: Screens) {

    @Named("main")
    @Provides
    fun mainRouter(): BaseRouter = mainCicerone.router

    @Named("main")
    @Provides
    fun mainNavigatorHolder(): NavigatorHolder = mainCicerone.getNavigatorHolder()

    @Provides
    fun screens(): Screens = screens
}