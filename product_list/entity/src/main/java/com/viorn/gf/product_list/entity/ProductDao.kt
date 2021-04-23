package com.viorn.gf.product_list.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.viorn.gf.product_list.entity.ProductEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductEntity")
    fun flowableProductList(): Flowable<List<ProductEntity>>

    @Query("SELECT * FROM ProductEntity")
    fun getProductList(): Single<List<ProductEntity>>

    @Query("SELECT MAX(id) FROM ProductEntity LIMIT 1")
    fun getLastId(): Single<Int>

    @Insert
    fun insert(list: List<ProductEntity>): Completable

    @Query("DELETE FROM ProductEntity")
    fun deleteAll(): Completable
}