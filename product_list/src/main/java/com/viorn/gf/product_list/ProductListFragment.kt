package com.viorn.gf.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.viorn.gf.core.di.ApplicationComponent


class ProductListFragment : Fragment() {
    private val productListComponent: ProductListComponent by lazy {
        DaggerProductListComponent.builder()
            .applicationComponent(ApplicationComponent.INSTANCE)
            .build()
    }
    private val viewModel: ProductListViewModel get() = ViewModelProvider(viewModelStore, productListComponent.productListViewModelFactory()).get(ProductListViewModel::class.java)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productListComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadingLiveData.observe(this, {

        })
        viewModel.productListLiveData.observe(this, {

        })
        viewModel.loadFirstPage()
    }
}