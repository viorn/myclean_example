package com.viorn.gf.product_list.domain

import com.viorn.gf.common.Converter
import com.viorn.gf.prodict_list.boundary.IProductListInteractor
import com.viorn.gf.prodict_list.boundary.ProductItemAdapterItem
import com.viorn.gf.product_list.entity.GetProductListResponse
import com.viorn.gf.product_list.entity.ProductDao
import com.viorn.gf.product_list.entity.ProductEntity
import com.viorn.gf.product_list.entity.ProductListRestService
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class ProductListInteractor(
    private val scheduler: Scheduler,
    private val productListRest: ProductListRestService,
    private val productDao: ProductDao,
    private val dbToUiConverter: Converter<ProductEntity, ProductItemAdapterItem>,
    private val restToDbConverter: Converter<GetProductListResponse.Product, ProductEntity>
) : IProductListInteractor {
    override fun loadFirstPage(): Flowable<List<ProductItemAdapterItem>> {
        return productDao.deleteAll().subscribeOn(Schedulers.io())
            .observeOn(scheduler)
            .andThen(productListRest.getProductList())
            .toFlowable()
            .map {
                val dbData = it.list.map(restToDbConverter::convert)
                productDao.insert(dbData).subscribeOn(Schedulers.io()).subscribe()
                dbData.map(dbToUiConverter::convert)
            }
    }

    override fun loadNextPage(): Flowable<List<ProductItemAdapterItem>> {
        return productDao.getLastId()
            .observeOn(scheduler)
            .flatMap {
                productListRest.getProductList(lastId = it)
            }
            .toFlowable()
            .map {
                val dbData = it.list.map(restToDbConverter::convert)
                productDao.insert(dbData).subscribeOn(Schedulers.io()).subscribe()
                dbData.map(dbToUiConverter::convert)
            }
    }

    override fun behavior(): Flowable<List<ProductItemAdapterItem>> {
        return productDao.flowableProductList().observeOn(scheduler).map { it.map(dbToUiConverter::convert) }
    }

    override fun get(): Single<List<ProductItemAdapterItem>> {
        return productDao.getProductList().observeOn(scheduler).map { it.map(dbToUiConverter::convert) }
    }
}