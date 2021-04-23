package com.viorn.gf.product_list.domain

import com.viorn.gf.prodict_list.boundary.ProductItemAdapterItem

data class ProductItemAdapterItemImpl(
    override val id: Int,
    override val title: String,
    override val description: String,
    override val images: List<String>
) : ProductItemAdapterItem {
}