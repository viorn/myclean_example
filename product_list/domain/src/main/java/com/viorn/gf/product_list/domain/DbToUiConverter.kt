package com.viorn.gf.product_list.domain

import com.viorn.gf.common.Converter
import com.viorn.gf.prodict_list.boundary.ProductItemAdapterItem
import com.viorn.gf.product_list.entity.GetProductListResponse
import com.viorn.gf.product_list.entity.ProductEntity

class DbToUiConverter : Converter<ProductEntity, ProductItemAdapterItem> {
    override fun convert(dbData: ProductEntity): ProductItemAdapterItem {
        return ProductItemAdapterItemImpl(dbData.id, dbData.name, dbData.description, dbData.images)
    }
}