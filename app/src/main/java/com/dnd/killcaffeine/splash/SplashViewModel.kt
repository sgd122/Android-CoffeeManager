/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.splash

import android.content.SharedPreferences
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.SharedPreferenceKey
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.CoffeeRepository
import com.dnd.killcaffeine.model.data.Personal
import com.dnd.killcaffeine.model.data.room.menu.Menu
import com.dnd.killcaffeine.model.data.room.menu.MenuDatabase
import com.dnd.killcaffeine.utils.SingleLiveEvent
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashViewModel(private val mRepository: CoffeeRepository,
                      private val mSharedPref: SharedPreferences) : BaseViewModel() {

    companion object {
        private const val START_ACTIVITY_POST_DELAY: Long = 2000
    }

    private val _startActivityLiveData = SingleLiveEvent<Any>()
    val startActivityLiveData: LiveData<Any> get() = _startActivityLiveData

    private val _savedPersonalRecommendLiveData = MutableLiveData<Int>()
    val savedPersonalRecommend: LiveData<Int> get() = _savedPersonalRecommendLiveData

    private val _savedTotalIntakeLiveData = MutableLiveData<Int>()
    val savedTotalIntakeLiveData: LiveData<Int> get() = _savedTotalIntakeLiveData

    fun startMainActivityAfterPostDelay(){
        Handler().postDelayed({

            _startActivityLiveData.call()

        }, START_ACTIVITY_POST_DELAY)
    }

    fun loadSavedTotalIntake(){
        addDisposable(mRepository.getAllMenu()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                val arrayList: ArrayList<Menu> = list as ArrayList
                var total = 0

                for (menu: Menu in arrayList) {
                    total += menu.caffeine
                }

                _savedTotalIntakeLiveData.postValue(total)

            }, {
                _savedTotalIntakeLiveData.postValue(0)
            }))
    }

    fun getPersonalRecommendCaffeeine(){
        addDisposable(
            Observable.just(mSharedPref.getString(SharedPreferenceKey.PUT_PERSONAL_INFO, ""))
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({ personal ->
                    personal?.let {
                        val savedPersonalInfo: Personal = Gson().fromJson(it, Personal::class.java)
                        _savedPersonalRecommendLiveData.postValue(savedPersonalInfo.recommendIntake)

                    }
                }, {
                    _savedPersonalRecommendLiveData.postValue(0)
                })
        )
    }
}