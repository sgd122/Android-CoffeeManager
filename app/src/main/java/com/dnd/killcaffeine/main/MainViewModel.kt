/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import com.dnd.killcaffeine.SharedPreferenceKey
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.utils.SingleLiveEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mSharedPref: SharedPreferences) : BaseViewModel() {

    companion object {
        private const val TAG: String = "MainViewModel"
    }

    private var pressedTime: Long = 0

    private val _midNightAlarmLiveData = SingleLiveEvent<Any>()
    val midNightAlarmLiveData: LiveData<Any> get() = _midNightAlarmLiveData

    fun onBackPressed(): Boolean{
        return when(System.currentTimeMillis() > pressedTime + 2000){
            true -> {
                pressedTime = System.currentTimeMillis()
                showSnackbar("뒤로가기를 한번 더 누르시면 종료됩니다.")
                false
            }
            false -> {
                true
            }
        }
    }

    fun checkSavedMidNightAlarm(){
        addDisposable(Observable.just(mSharedPref.getString(SharedPreferenceKey.PUT_CLEAR_ROOM, ""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ value ->

                value?.let {
                    if(it == "") { // 저장된 알람이 없다면, LiveData call
                        _midNightAlarmLiveData.call()
                    }
                }

            }, {
                Log.d(TAG, "error : ${it.message}")
            })
        )
    }

    fun saveMidNightAlarmPreference(){
        addDisposable(Observable.just(mSharedPref.edit().putString(SharedPreferenceKey.PUT_CLEAR_ROOM, "mid_night_alarm_save").commit())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                Log.d(TAG, "자정 룸 클리어 알람 등록 완료")

            }, {
                Log.d(TAG, "error : ${it.message}")
            })
        )
    }
}