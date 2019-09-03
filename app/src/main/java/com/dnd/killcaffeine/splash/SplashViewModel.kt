/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.splash

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.data.menu.MenuDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashViewModel(private val mMenuDatabase: MenuDatabase) : BaseViewModel() {

    companion object {
        private const val START_ACTIVITY_POST_DELAY: Long = 2000
        private const val INVALID_INTAKE_RESULT: Int = -1
    }

    private val _startActivityLiveData = MutableLiveData<Boolean>()
    val startActivityLiveData: LiveData<Boolean> get() = _startActivityLiveData

    private val _caffeineIntakeLiveData = MutableLiveData<Int>()
    val caffeineIntakeLiveData: LiveData<Int> get() = _caffeineIntakeLiveData

    fun startMainActivityAfterPostDelay(){
        Handler().postDelayed({
            _startActivityLiveData.value = true

        }, START_ACTIVITY_POST_DELAY)
    }

    fun loadTotalTodayCaffeineIntake(){
        addDisposable(mMenuDatabase.menuDao.getAllMenu()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                list?.let {
                    var totalIntake = 0
                    it.forEach {  history ->
                        totalIntake += history.caffeine
                    }

                    _caffeineIntakeLiveData.value = totalIntake
                }
            }, {
                _caffeineIntakeLiveData.value = INVALID_INTAKE_RESULT
            }))
    }
}