package com.viorn.gf.product_list

import androidx.lifecycle.MutableLiveData
import com.viorn.gf.prodict_list.boundary.IProductListInteractor
import com.viorn.gf.prodict_list.boundary.ProductItemAdapterItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    private val productListInteractor: IProductListInteractor
) : AProductListViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override val productListLiveData = MutableLiveData<List<ProductItemAdapterItem>>().apply {
        productListInteractor.get().subscribe { data, error ->
            if (error == null) {
                this@apply.value = data
            }
        }.addTo(compositeDisposable)
    }
    override val loadingLiveData = MutableLiveData<Boolean>().apply {
        value = false
    }

    init {
        //productListInteractor.behavior().subscribe {
        //    productListLiveData.value = it
        //}.addTo(compositeDisposable)
    }

    override fun loadFirstPage() {
        loadingLiveData.value = true
        productListInteractor.loadFirstPage().subscribe(
            {
                productListLiveData.value = it
            },
            {

            },
            {
                loadingLiveData.value = false
            }
        ).addTo(compositeDisposable)
    }

    override fun loadNextPage() {
        loadingLiveData.value = true
        productListInteractor.loadNextPage().subscribe(
            {
                productListLiveData.value = productListLiveData.value?.plus(it)
            },
            {

            },
            {
                loadingLiveData.value = false
            }
        ).addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}