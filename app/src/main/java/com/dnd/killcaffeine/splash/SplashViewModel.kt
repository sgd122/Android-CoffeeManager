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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashViewModel(private val mMenuDatabase: MenuDatabase) : BaseViewModel() {

    companion object {
        private const val START_ACTIVITY_POST_DELAY: Long = 2000
        private const val INVALID_INTAKE_RESULT: Int = -1
    }

    private val _startActivityLiveData = MutableLiveData<Pair<Int, ArrayList<Menu>>>()
    val startActivityLiveData: LiveData<Pair<Int, ArrayList<Menu>>> get() = _startActivityLiveData

    private val _loadSavedHistoryLiveData = MutableLiveData<ArrayList<Menu>>()
    val loadSavedHistoryLiveData: LiveData<ArrayList<Menu>> get() = _loadSavedHistoryLiveData

    fun startMainActivityAfterPostDelay(menuList : ArrayList<Menu>){
        Handler().postDelayed({
            var totalCaffeine = 0
            menuList.forEach {
                totalCaffeine += it.caffeine
            }

            _startActivityLiveData.postValue(Pair(totalCaffeine, menuList))

        }, START_ACTIVITY_POST_DELAY)
    }

    fun loadTotalTodayCaffeineIntake(){
        addDisposable(mMenuDatabase.menuDao.getAllMenu()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                list?.let {
                    _loadSavedHistoryLiveData.value = it as ArrayList

                }
            }, {
                _loadSavedHistoryLiveData.value = ArrayList()
            }))
    }
}