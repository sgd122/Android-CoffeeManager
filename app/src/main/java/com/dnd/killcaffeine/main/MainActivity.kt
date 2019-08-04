package com.dnd.killcaffeine.main

import androidx.lifecycle.ViewModelProviders
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val resourceId: Int
        get() = R.layout.activity_main

    override val mViewModel: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    override fun initViewStart() {

        activity_main_tab_layout.apply {
            TabLayoutMediator(this, activity_main_view_pager2, true,
                TabLayoutMediator.OnConfigureTabCallback { tab, position ->

                }).attach()
        }
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
    }
}
