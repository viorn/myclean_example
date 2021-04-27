package com.viorn.gf.product_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viorn.gf.core.di.ApplicationComponent
import com.viorn.multitypeadapter.MultiTypeAdapter
import kotlinx.android.synthetic.main.fragment_product_list.*


class ProductListFragment : Fragment() {
    private val productListComponent: ProductListComponent by lazy {
        DaggerProductListComponent.builder()
            .applicationComponent(ApplicationComponent.INSTANCE)
            .build()
    }
    private val viewModel: ProductListViewModel get() = ViewModelProvider(viewModelStore, productListComponent.productListViewModelFactory()).get(ProductListViewModel::class.java)

    private val adapter by lazy {
        MultiTypeAdapter()
            .registerRenderer(ProductAdapterRenderer.Item::class.java, ProductAdapterRenderer())
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

        rv_products.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_products.adapter = adapter

        viewModel.loadingLiveData.observe(this, {

        })
        viewModel.productListLiveData.observe(this, { list ->
            adapter.applyData(list.map { ProductAdapterRenderer.Item(it) })
        })
        viewModel.loadFirstPage()
    }
}