package com.viorn.gf

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.viorn.gf.core.di.Screens
import com.viorn.gf.product_list.ProductListFragment

class ScreensImpl: Screens {
    override fun main() = FragmentScreen { MainFragment() }
    override fun productList() = FragmentScreen{ ProductListFragment() }
}