package com.viorn.gf.domain_di

import com.viorn.gf.core.db.AppDatabase
import com.viorn.gf.prodict_list.boundary.IProductListInteractor
import com.viorn.gf.product_list.domain.DbToUiConverter
import com.viorn.gf.product_list.domain.ProductListInteractor
import com.viorn.gf.product_list.domain.RestToDbConverter
import com.viorn.gf.product_list.entity.ProductDao
import com.viorn.gf.product_list.entity.ProductListRestService
import com.viorn.gf.product_list_domain.mock.MockProductListRestService
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import retrofit2.Retrofit
import javax.inject.Named

@Module
class ProductListInteractorModule() {
    @Provides
    fun productListInteractor(
        @Named("Interactor") scheduler: Scheduler,
        productListRestService: ProductListRestService,
        ProductDao: ProductDao
    ): IProductListInteractor {
        return ProductListInteractor(scheduler, productListRestService, ProductDao, DbToUiConverter(), RestToDbConverter())
    }

    @Provides
    fun productDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }

    @Provides
    fun productListRestService(retrofit: Retrofit): ProductListRestService {
        return MockProductListRestService()
        //return retrofit.create(ProductListRestService::class.java)
    }
}