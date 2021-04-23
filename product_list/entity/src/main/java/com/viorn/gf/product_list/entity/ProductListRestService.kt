package com.viorn.gf.product_list.entity

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductListRestService {
    @GET("/productList")
    fun getProductList(@Query("lastId") lastId: Int? = null, @Query("limit") limit: Int = 10): Single<GetProductListResponse>
}