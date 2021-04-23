package com.viorn.gf.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.viorn.gf.R
import com.viorn.gf.core.di.ApplicationComponent

class MainActivity : AppCompatActivity() {


    private val navigator by lazy { AppNavigator(this, R.id.main_container) }

    override fun onResumeFragments() {
        super.onResumeFragments()
        ApplicationComponent.INSTANCE?.mainNavigatorHolder()!!.setNavigator(navigator)
    }

    override fun onPause() {
        ApplicationComponent.INSTANCE?.mainNavigatorHolder()!!.removeNavigator()
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}