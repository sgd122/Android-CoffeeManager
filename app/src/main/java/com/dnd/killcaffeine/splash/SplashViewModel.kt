/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.splash

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.data.room.menu.Menu
import com.dnd.killcaffeine.model.data.room.menu.MenuDatabase
import com.dnd.killcaffeine.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashViewModel(private val mMenuDatabase: MenuDatabase) : BaseViewModel() {

    companion object {
        private const val START_ACTIVITY_POST_DELAY: Long = 2000
    }

    private val _startActivityLiveData = SingleLiveEvent<Any>()
    val startActivityLiveData: LiveData<Any> get() = _startActivityLiveData

    fun startMainActivityAfterPostDelay(){
        Handler().postDelayed({

            _startActivityLiveData.call()

        }, START_ACTIVITY_POST_DELAY)
    }
}