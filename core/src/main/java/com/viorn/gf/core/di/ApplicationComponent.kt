package com.viorn.gf.core.di

import android.content.Context
import com.github.terrakok.cicerone.BaseRouter
import com.github.terrakok.cicerone.NavigatorHolder
import com.viorn.gf.core.db.AppDatabase
import dagger.Component
import io.reactivex.Scheduler
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CoreScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewScope

@CoreScope
@Component(modules = [AppContextModule::class,
    CiceroneModule::class,
    CoreRestModule::class,
    DatabaseModule::class,
    SchedulersModule::class])
interface ApplicationComponent {
    companion object {
        var INSTANCE: ApplicationComponent? = null
    }

    fun context(): Context

    @Named("main")
    fun mainRouter(): BaseRouter

    @Named("main")
    fun mainNavigatorHolder(): NavigatorHolder

    fun screens(): Screens

    fun retrofit(): Retrofit

    fun appDatabase(): AppDatabase

    @Named("Interactor")
    fun interactorSheduler(): Scheduler
}

val SCREENS: Screens get() = ApplicationComponent.INSTANCE!!.screens()!!

