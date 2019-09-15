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

        mViewModel.startActivityLiveData.observe(this, Observer {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        })
    }

    override fun initViewFinal() {
        mViewModel.startMainActivityAfterPostDelay()
    }
}