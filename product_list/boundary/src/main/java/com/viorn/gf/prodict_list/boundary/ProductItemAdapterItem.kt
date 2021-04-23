package com.viorn.gf.prodict_list.boundary

interface ProductItemAdapterItem {
    val id: Int
    val title: String
    val description: String
    val images: List<String>
}