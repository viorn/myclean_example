package com.viorn.gf.product_list.domain

import com.viorn.gf.prodict_list.boundary.IProductListInteractor
import com.viorn.gf.prodict_list.boundary.ProductItemAdapterItem
import com.viorn.gf.product_list.entity.GetProductListResponse
import com.viorn.gf.product_list.entity.ProductDao
import com.viorn.gf.product_list.entity.ProductEntity
import com.viorn.gf.product_list.entity.ProductListRestService
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single


class ProductListInteractor(
    private val scheduler: Scheduler,
    private val productListRest: ProductListRestService,
    private val ProductDao: ProductDao
) : IProductListInteractor {
    override fun loadFirstPage(): Flowable<List<ProductItemAdapterItem>> {
        return ProductDao.deleteAll()
            .observeOn(scheduler)
            .andThen(productListRest.getProductList())
            .toFlowable()
            .map {
                val dbData = it.list.map(restToDb)
                ProductDao.insert(dbData)
                dbData.map(dbToUI)
            }
    }

    override fun loadNextPage(): Flowable<List<ProductItemAdapterItem>> {
        return ProductDao.getLastId()
            .observeOn(scheduler)
            .flatMap {
                productListRest.getProductList(lastId = it)
            }
            .toFlowable()
            .map {
                val dbData = it.list.map(restToDb)
                ProductDao.insert(dbData)
                dbData.map(dbToUI)
            }
    }

    override fun behavior(): Flowable<List<ProductItemAdapterItem>> {
        return ProductDao.flowableProductList().map { it.map(dbToUI) }
    }

    override fun get(): Single<List<ProductItemAdapterItem>> {
        return ProductDao.getProductList().map { it.map(dbToUI) }
    }

    private val restToDb: (GetProductListResponse.Product) -> ProductEntity = { restData ->
        ProductEntity(
            id = restData.id,
            name = restData.name
        )
    }

    private val dbToUI: (ProductEntity) -> ProductItemAdapterItem = { dbData ->
        object : ProductItemAdapterItem {

        }
    }
}