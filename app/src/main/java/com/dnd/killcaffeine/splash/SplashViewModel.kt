/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.splash

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel

class SplashViewModel : BaseViewModel() {

    private val START_ACTIVITY_POST_DELAY: Long = 2500

    private val _startActivityLiveData = MutableLiveData<Boolean>()
    val startActivityLiveData: LiveData<Boolean> get() = _startActivityLiveData

    fun startMainActivityAfterPostDelay(){
        Handler().postDelayed({
            _startActivityLiveData.value = true

        }, START_ACTIVITY_POST_DELAY)
    }
}