package com.viorn.gf.core.di

import android.content.Context
import androidx.room.Room
import com.viorn.gf.core.db.AppDatabase
import com.viorn.gf.product_list.entity.ProductDao
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {
    @Provides
    fun appDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "main"
        ).build()
    }

    @Provides
    fun productDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }
}