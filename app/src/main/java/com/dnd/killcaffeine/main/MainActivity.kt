package com.dnd.killcaffeine.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityMainBinding
import com.dnd.killcaffeine.main.home.MainHomeFragment
import com.dnd.killcaffeine.main.settings.MainSettingsFragment
import com.dnd.killcaffeine.main.statistics.MainStatisticsFragment
import com.dnd.killcaffeine.model.data.room.menu.Menu
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val resourceId: Int
        get() = R.layout.activity_main

    override val mViewModel: MainViewModel by viewModel()

    override fun initViewStart() {
        supportFragmentManager.beginTransaction().add(R.id.activity_main_container, getFragment(0)).commitNow()

        activity_main_tab_layout.apply {
            addTab(newTab().setIcon(getTabIcon(TabComponent.HOME.ordinal)))
            addTab(newTab().setIcon(getTabIcon(TabComponent.STATISTICS.ordinal)))
            addTab(newTab().setIcon(getTabIcon(TabComponent.SETTINGS.ordinal)))
            tabGravity = TabLayout.GRAVITY_FILL

            addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    with(R.id.activity_main_container){
                        when(tab?.position){
                            TabComponent.HOME.ordinal -> supportFragmentManager.beginTransaction().replace(this, getFragment(TabComponent.HOME.ordinal))
                            TabComponent.STATISTICS.ordinal -> supportFragmentManager.beginTransaction().replace(this, getFragment(TabComponent.STATISTICS.ordinal))
                            TabComponent.SETTINGS.ordinal -> supportFragmentManager.beginTransaction().replace(this, getFragment(TabComponent.SETTINGS.ordinal))
                            else -> throw IllegalStateException("올바르지 않은 접근입니다")
                        }.also {
                            it.commit()
                        }
                    }
                }
            })
        }
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
        if(intent.hasExtra(RequestCode.TOTAL_TODAY_CAFFEINE_INTAKE_SPLASH_TO_MAIN)){
            val totalTodayIntake:Int = intent.getIntExtra(RequestCode.TOTAL_TODAY_CAFFEINE_INTAKE_SPLASH_TO_MAIN, 0)
            val menuList: ArrayList<Menu> = intent.getSerializableExtra(RequestCode.TOTAL_TODAY_MENU_LIST_SPLASH_TO_MAIN) as ArrayList<Menu>

            getFragment(TabComponent.HOME.ordinal).arguments =  Bundle().apply { // Bundle 에 담아서 Fragment 에게 전달
                putInt(RequestCode.TOTAL_TODAY_CAFFEINE_INTAKE_MAIN_TO_FRAGMENT, totalTodayIntake)
                putSerializable(RequestCode.TOTAL_TODAY_MENU_LIST_MAIN_TO_FRAGMENT, menuList)
            }
        }
    }

    override fun onBackPressed() {
        if(mViewModel.onBackPressed()){
            super.onBackPressed()
        }
    }

    companion object {
        enum class TabComponent {
            HOME, STATISTICS, SETTINGS
        }

        private val fragmentList = listOf(
            MainHomeFragment.newInstance(),
            MainStatisticsFragment.newInstance(),
            MainSettingsFragment.newInstance()
        )

        private val tabIconList = listOf(
            R.drawable.icon_tab_home_selector,
            R.drawable.icon_tab_statistics_selector,
            R.drawable.icon_tab_settings_selector
        )

        fun getTabIcon(position: Int): Int = tabIconList[position]
        fun getFragment(position: Int): Fragment = fragmentList[position]
    }
}
