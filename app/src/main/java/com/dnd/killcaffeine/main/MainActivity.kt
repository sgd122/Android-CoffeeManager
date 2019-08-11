package com.dnd.killcaffeine.main

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityMainBinding
import com.dnd.killcaffeine.main.home.MainHomeFragment
import com.dnd.killcaffeine.main.settings.MainSettingsFragment
import com.dnd.killcaffeine.main.statistics.MainStatisticsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val resourceId: Int
        get() = R.layout.activity_main

    override val mViewModel: MainViewModel by viewModel()

    override fun initViewStart() {
        supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, getFragment(0)).commitNow()

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
                            it.addToBackStack(null)
                        }
                    }
                }
            })
        }
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
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
