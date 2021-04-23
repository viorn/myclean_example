package com.viorn.gf.product_list_domain.mock

import com.viorn.gf.product_list.entity.GetProductListResponse
import com.viorn.gf.product_list.entity.ProductListRestService
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class MockProductListRestService: ProductListRestService {
    override fun getProductList(lastId: Int?, limit: Int): Single<GetProductListResponse> {
        return Single.fromCallable {
            GetProductListResponse(
                list = emptyList()
            )
        }.delay(200, TimeUnit.MILLISECONDS)
    }
}