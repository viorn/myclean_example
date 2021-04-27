package com.viorn.gf.product_list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.viorn.gf.prodict_list.boundary.ProductItemAdapterItem
import com.viorn.multitypeadapter.MultiTypeAdapter
import kotlinx.android.synthetic.main.product_list_item.view.*


class ProductAdapterRenderer: MultiTypeAdapter.Renderer<ProductAdapterRenderer.Item>() {
    data class Item (val value: ProductItemAdapterItem): MultiTypeAdapter.AdapterItem {
        override fun getItemId(): Long {
            return value.id.toLong()
        }
    }

    override fun bindItem(viewHolder: MultiTypeAdapter.MultiTypeViewHolder, model: Item) {
        viewHolder.itemView.apply {
            tv_name.text = model.value.title
        }
    }

    override fun createViewHolder(parent: ViewGroup): MultiTypeAdapter.MultiTypeViewHolder {
        return MultiTypeAdapter.MultiTypeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_list_item, parent, false)
        )
    }
}