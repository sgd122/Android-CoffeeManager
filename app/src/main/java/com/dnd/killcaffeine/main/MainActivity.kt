package com.dnd.killcaffeine.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import com.dnd.killcaffeine.BroadcastReceiverKey
import com.dnd.killcaffeine.CoffeeManagerApplication
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.broadcast.ActionDateChangedReceiver
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
import java.util.*

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
                        TabComponent.HOME.ordinal -> supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, getFragment(TabComponent.HOME.ordinal)).commitAllowingStateLoss()
                        TabComponent.STATISTICS.ordinal -> supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, getFragment(TabComponent.STATISTICS.ordinal)).commitAllowingStateLoss()
                        TabComponent.SETTINGS.ordinal -> supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, getFragment(TabComponent.SETTINGS.ordinal)).commitAllowingStateLoss()
                        else -> throw IllegalStateException("올바르지 않은 접근입니다")
                    }
                }
            })
        }
    }

    override fun initDataBinding() {

        mViewModel.midNightAlarmLiveData.observe(this, androidx.lifecycle.Observer {
            deleteRoomDateChanged()
            mViewModel.saveMidNightAlarmPreference()
        })
    }

    override fun initViewFinal() {

        mViewModel.checkSavedMidNightAlarm()

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

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("알람", "MainActivity On New Intent")

    }

    private fun getTabIcon(position: Int): Int = tabIconList[position]
    fun getFragment(position: Int): Fragment = fragmentList[position]


    private fun getFirebaseTokenInstance(){
        // 파이어 베이스 토큰을 생성하는 함수

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if(!task.isSuccessful) {
                    Logger.w("파이어베이스 getInstance is failed ${task.exception}")
                }

                val token = task.result?.token
                Logger.d("Token : $token")
            }
    }

    private fun deleteRoomDateChanged() {
        // 자정이 되면 호출될 알람매니저

        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        val pendingIntent: PendingIntent? = PendingIntent.getBroadcast(applicationContext,
            BroadcastReceiverKey.ACTION_DATE_CHANGE_REQ_CODE,
            Intent(this, ActionDateChangedReceiver::class.java),
            0
        )

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP, // Sleep 상태에서도 동작 가능
            calendar.timeInMillis, // Trigger Time, 과거로 설정되어 있다면, 즉시 동작
            AlarmManager.INTERVAL_DAY, // 하루마다 반
            pendingIntent
        )
    }
}
