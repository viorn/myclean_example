package com.viorn.gf.product_list.domain

import com.viorn.gf.common.Converter
import com.viorn.gf.product_list.entity.GetProductListResponse
import com.viorn.gf.product_list.entity.ProductEntity
import java.util.function.Function

class RestToDbConverter : Converter<GetProductListResponse.Product, ProductEntity> {
    override fun convert(restData: GetProductListResponse.Product): ProductEntity {
        return ProductEntity(
            id = restData.id,
            name = restData.name,
            description = restData.descriptions,
            images = restData.images
        )
    }
}