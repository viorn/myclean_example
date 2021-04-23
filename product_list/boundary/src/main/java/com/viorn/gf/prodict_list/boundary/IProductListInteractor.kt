package com.viorn.gf.prodict_list.boundary

import io.reactivex.Flowable
import io.reactivex.Single

interface IProductListInteractor {
    //return only new items
    fun loadFirstPage(): Flowable<List<ProductItemAdapterItem>>
    //return only new items
    fun loadNextPage(): Flowable<List<ProductItemAdapterItem>>
    //return all items from db
    fun behavior(): Flowable<List<ProductItemAdapterItem>>
    //return all items from db
    fun get(): Single<List<ProductItemAdapterItem>>
}