package com.dnd.killcaffeine.main

import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val resourceId: Int
        get() = R.layout.activity_main

    override val mViewModel: MainViewModel by viewModel()

    private lateinit var mViewPager2: ViewPager2
    private lateinit var mTabLayout: TabLayout
    private val mMainViewPagerAdapter by lazy { MainViewPagerAdapter(this) }

    override fun initViewStart() {

        mViewPager2 = activity_main_view_pager2.apply {
            adapter = mMainViewPagerAdapter
        }

        mTabLayout = activity_main_tab_layout.apply {
            TabLayoutMediator(this, mViewPager2, true, TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                tab.icon = ContextCompat.getDrawable(this@MainActivity, MainViewPagerAdapter.getTabIcon(position))
            }).attach()
        }

    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
    }
}
