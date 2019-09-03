/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.splash

import android.content.Intent
import androidx.lifecycle.Observer
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivitySplashBinding
import com.dnd.killcaffeine.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val resourceId: Int
        get() = R.layout.activity_splash

    override val mViewModel: SplashViewModel by viewModel()

    override fun initViewStart() {
    }

    override fun initDataBinding() {

        mViewModel.loadSavedHistoryLiveData.observe(this, Observer { list ->

            mViewModel.startMainActivityAfterPostDelay(list)
        })

        mViewModel.startActivityLiveData.observe(this, Observer { pair ->
            pair?.let {
                startActivity(Intent(this, MainActivity::class.java).apply {
                    putExtra(RequestCode.TOTAL_TODAY_CAFFEINE_INTAKE_SPLASH_TO_MAIN, pair.first)
                    putExtra(RequestCode.TOTAL_TODAY_MENU_LIST_SPLASH_TO_MAIN, pair.second)
                })
                finish()
            }
        })
    }

    override fun initViewFinal() {
        mViewModel.loadTotalTodayCaffeineIntake()
    }
}