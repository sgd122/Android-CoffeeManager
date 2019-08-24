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
import com.dnd.killcaffeine.model.data.history.HistoryDatabase
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val resourceId: Int
        get() = R.layout.activity_splash

    override val mViewModel: SplashViewModel by viewModel()
    private val mHistoryDatabase: HistoryDatabase by inject()

    private var caffeineIntake: Int = 0

    override fun initViewStart() {
    }

    override fun initDataBinding() {
        mViewModel.caffeineIntakeLiveData.observe(this, Observer {
            it?.let {
                caffeineIntake = it
                mViewModel.startMainActivityAfterPostDelay()
            }
        })

        mViewModel.startActivityLiveData.observe(this, Observer {
            it?.let {
                startActivity(Intent(this, MainActivity::class.java).apply {
                    putExtra(RequestCode.TOTAL_TODAY_CAFFEINE_INTAKE_SPLASH_TO_MAIN, caffeineIntake)
                })
                finish()
            }
        })
    }

    override fun initViewFinal() {
        mViewModel.loadTotalTodayCaffeineIntake(mHistoryDatabase)
    }
}