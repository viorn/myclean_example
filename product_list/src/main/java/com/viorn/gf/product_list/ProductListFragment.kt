package com.viorn.gf.product_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.viorn.gf.core.di.ApplicationComponent
import com.viorn.multitypeadapter.MultiTypeAdapter


class ProductListFragment : Fragment() {
    private val productListComponent: ProductListComponent by lazy {
        DaggerProductListComponent.builder()
            .applicationComponent(ApplicationComponent.INSTANCE)
            .build()
    }
    private val viewModel: ProductListViewModel get() = ViewModelProvider(viewModelStore, productListComponent.productListViewModelFactory()).get(ProductListViewModel::class.java)

    private val adapter by lazy {
        MultiTypeAdapter()
    }

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
        Log.d("DEBUG", "onViewCreated");
        viewModel.loadingLiveData.observe(this, {

        })
        viewModel.productListLiveData.observe(this, {
            Log.d("DEBUG", it.toString());
        })
        viewModel.loadFirstPage()
    }
}