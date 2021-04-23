package com.viorn.gf.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viorn.gf.core.di.ApplicationComponent
import com.viorn.gf.core.di.ViewScope
import com.viorn.gf.domain_di.ProductListInteractorModule
import com.viorn.gf.prodict_list.boundary.IProductListInteractor
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named

@ViewScope
@Component(dependencies = [ApplicationComponent::class], modules = [ProductViewModelModule::class, ProductListInteractorModule::class])
interface ProductListComponent {
    fun inject(fragment: ProductListFragment)
    fun inject(viewModel: ProductListViewModel)

    @Named("ProductListViewModel")
    fun productListViewModelFactory(): ViewModelProvider.Factory
}

@Module
class ProductViewModelModule {
    @Provides
    fun productListViewModel(productListInteractor: IProductListInteractor): AProductListViewModel {
        return ProductListViewModel(productListInteractor)
    }

    @Named("ProductListViewModel")
    @Provides
    fun productListViewModelFactory(productListInteractor: IProductListInteractor): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return productListViewModel(productListInteractor) as T
            }
        }
    }
}