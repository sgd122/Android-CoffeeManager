package com.dnd.killcaffeine.main

import androidx.fragment.app.Fragment
import com.dnd.killcaffeine.CoffeeManagerApplication
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivityMainBinding
import com.dnd.killcaffeine.main.home.MainHomeFragment
import com.dnd.killcaffeine.main.settings.MainSettingsFragment
import com.dnd.killcaffeine.main.statistics.MainStatisticsFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.tabs.TabLayout
import com.google.firebase.iid.FirebaseInstanceId
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    companion object {
        var savedCaffeineIntake:Int = 0
        var savedPersonalRecommend: Int = 0
    }

    override val resourceId: Int
        get() = R.layout.activity_main

    override val mViewModel: MainViewModel by viewModel()

    enum class TabComponent {
        HOME, STATISTICS, SETTINGS
    }

    private val fragmentList = listOf(
        MainHomeFragment(),
        MainStatisticsFragment(),
        MainSettingsFragment()
    )

    private val tabIconList = listOf(
        R.drawable.icon_tab_home_selector,
        R.drawable.icon_tab_statistics_selector,
        R.drawable.icon_tab_settings_selector
    )

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
                    when(tab?.position){
                        TabComponent.HOME.ordinal -> supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, getFragment(TabComponent.HOME.ordinal)).commit()
                        TabComponent.STATISTICS.ordinal -> supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, getFragment(TabComponent.STATISTICS.ordinal)).commit()
                        TabComponent.SETTINGS.ordinal -> supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, getFragment(TabComponent.SETTINGS.ordinal)).commit()
                        else -> throw IllegalStateException("올바르지 않은 접근입니다")
                    }
                }
            })
        }
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
        if(intent.hasExtra(RequestCode.SAVED_TOTAL_CAFFIEINE_INTAKE) && intent.hasExtra(RequestCode.SAVED_PERSONAL_RECOMMEND)){
            savedCaffeineIntake = intent.getIntExtra(RequestCode.SAVED_TOTAL_CAFFIEINE_INTAKE, 0)
            savedPersonalRecommend = intent.getIntExtra(RequestCode.SAVED_PERSONAL_RECOMMEND, 0)

            /*getFragment(TabComponent.HOME.ordinal).arguments =  Bundle().apply { // Bundle 에 담아서 Fragment 에게 전달
                putInt(RequestCode.BUNDLE_TOTAL_CAFFEINE_INTAKE, savedCaffeineIntake)
                putInt(RequestCode.BUNDLE_PERSONAL_RECOMMEND, savedPersonalRecommend)
            }*/
        }
    }

    override fun onBackPressed() {
        if(mViewModel.onBackPressed()){
            super.onBackPressed()
        }
    }

    private fun getTabIcon(position: Int): Int = tabIconList[position]
    fun getFragment(position: Int): Fragment = fragmentList[position]


    // 파이어 베이스 토큰을 생성하는 함수
    private fun getFirebaseTokenInstance(){
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if(!task.isSuccessful) {
                    Logger.w("파이어베이스 getInstance is failed ${task.exception}")
                }

                val token = task.result?.token
                Logger.d("Token : $token")
            }
    }
}
