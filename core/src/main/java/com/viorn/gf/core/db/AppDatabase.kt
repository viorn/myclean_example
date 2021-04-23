package com.viorn.gf.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.viorn.gf.product_list.entity.ProductDao
import com.viorn.gf.product_list.entity.ProductEntity


@Database(
    entities = [ProductEntity::class],
    version = 1
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}