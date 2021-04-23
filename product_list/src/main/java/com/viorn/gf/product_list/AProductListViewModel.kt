package com.viorn.gf.product_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.viorn.gf.prodict_list.boundary.ProductItemAdapterItem

abstract class AProductListViewModel : ViewModel() {
    abstract fun loadFirstPage()
    abstract fun loadNextPage()
    abstract val productListLiveData: LiveData<List<ProductItemAdapterItem>>
    abstract val loadingLiveData: LiveData<Boolean>
}