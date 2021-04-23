package com.viorn.gf.prodict_list.boundary

import io.reactivex.Flowable
import io.reactivex.Single

interface IProductListInteractor {
    fun loadFirstPage(): Flowable<List<ProductItemAdapterItem>>
    fun loadNextPage(): Flowable<List<ProductItemAdapterItem>>
    fun behavior(): Flowable<List<ProductItemAdapterItem>>
    fun get(): Single<List<ProductItemAdapterItem>>
}