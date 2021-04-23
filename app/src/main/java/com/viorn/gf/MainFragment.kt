package com.viorn.gf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.viorn.gf.product_list.ProductListFragment
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private val viewPagerAdapter by lazy { MyPagerAdapter() }
    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)

        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val id = when (position) {
                0 -> R.id.page_1
                1 -> R.id.page_2
                else -> throw IndexOutOfBoundsException()
            }
            if (menu_main.selectedItemId != id)
                menu_main.selectedItemId = id
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view_pager_main.adapter = viewPagerAdapter
        view_pager_main.registerOnPageChangeCallback(onPageChangeCallback)
        menu_main.setOnNavigationItemSelectedListener {
            val position = when (it.itemId) {
                R.id.page_1 -> 0
                R.id.page_2 -> 1
                else -> throw IndexOutOfBoundsException()
            }
            view_pager_main.setCurrentItem(position, true)
            return@setOnNavigationItemSelectedListener true
        }
    }

    inner class MyPagerAdapter :
        FragmentStateAdapter(this) {
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ProductListFragment()
                1 -> com.viorn.gf.calculator.CalculatorFragment()
                else -> throw IndexOutOfBoundsException()
            }
        }
    }
}