package com.viorn.gf.product_list_domain.mock

import android.util.Log
import com.viorn.gf.product_list.entity.GetProductListResponse
import com.viorn.gf.product_list.entity.ProductListRestService
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class MockProductListRestService: ProductListRestService {
    override fun getProductList(lastId: Int?, limit: Int): Single<GetProductListResponse> {
        Log.d("DEBUG", "getProductList");
        return Single.fromCallable {
            return@fromCallable GetProductListResponse(
                list = listOf(
                    GetProductListResponse.Product(
                        id = 1,
                        name = "Первый",
                        descriptions = "описание первого",
                        images = emptyList()
                    ),
                    GetProductListResponse.Product(
                        id = 2,
                        name = "Второй",
                        descriptions = "описание второго",
                        images = emptyList()
                    ),
                    GetProductListResponse.Product(
                        id = 3,
                        name = "Третий",
                        descriptions = "описание третьего",
                        images = emptyList()
                    )
                )
            )
        }.delay(200, TimeUnit.MILLISECONDS)
    }
}