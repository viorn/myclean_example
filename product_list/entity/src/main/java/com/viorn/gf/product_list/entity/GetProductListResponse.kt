package com.viorn.gf.product_list.entity

data class GetProductListResponse(val list: List<Product>) {
    data class Product(
        val id: Int,
        val name: String,
        val descriptions: String,
        val images: List<String>
    )
}