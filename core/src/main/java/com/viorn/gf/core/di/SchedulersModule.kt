package com.viorn.gf.core.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class SchedulersModule {
    @Named("Interactor")
    @com.viorn.gf.core.di.CoreScope
    @Provides
    fun interactorScheduler(): Scheduler {
        return Schedulers.single()
    }
}