/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.SharedPreferenceKey
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.CoffeeRepository
import com.dnd.killcaffeine.model.data.Personal
import com.dnd.killcaffeine.model.data.room.menu.Menu
import com.dnd.killcaffeine.model.data.room.menu.MenuDatabase
import com.dnd.killcaffeine.model.data.result.DecaffeineResult
import com.dnd.killcaffeine.model.remote.service.CoffeeManagerService
import com.dnd.killcaffeine.utils.SingleLiveEvent
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainHomeViewModel(private val mMenuDatabase: MenuDatabase,
                        private val mSharedPref: SharedPreferences,
                        private val mCoffeeRepository: CoffeeRepository) : BaseViewModel() {

    private val _decaffeineMenuLiveData = MutableLiveData<DecaffeineResult>()
    val decaffeineMenuLiveData: LiveData<DecaffeineResult> get() = _decaffeineMenuLiveData

    private val _refreshedHistoryLiveData = MutableLiveData<ArrayList<Menu>>()
    val refreshedHistoryLiveData: LiveData<ArrayList<Menu>> get() = _refreshedHistoryLiveData

    private val _exceededIntakeLiveData = SingleLiveEvent<Any?>()
    val exceededIntakeLiveData: LiveData<Any?> get() = _exceededIntakeLiveData

    private var totalIntakeFromSplash: Int = 0 // Splash 에서 넘겨받은 일일카페인 섭취량
    private var savedMenuList: ArrayList<Menu>? = null

    fun getDecaffeineMenuList(){
        startLoadingIndicator()
        addDisposable(mCoffeeRepository.getDecaffeineMenuList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                stopLoadingIndicator()
                _decaffeineMenuLiveData.postValue(result)

            }, {
                stopLoadingIndicator()
                showSnackbar("디카페인 정보를 불러오는데 실패했습니다.")
            }))
    }

    fun refreshHistoryFromRoomDatabase(){
        addDisposable(mMenuDatabase.menuDao.getAllMenu()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _refreshedHistoryLiveData.postValue(it as ArrayList<Menu>)

            }, {
                _refreshedHistoryLiveData.postValue(ArrayList())
            }))
    }

    fun checkExceedRecommendedQuantity(intake: Int){
        startLoadingIndicator()
        addDisposable(Observable.just(mSharedPref.getString(SharedPreferenceKey.PUT_PERSONAL_INFO, ""))
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({ personal ->
                stopLoadingIndicator()
                personal?.let {
                    val savedPersonalInfo: Personal = Gson().fromJson(it, Personal::class.java)

                    if(intake > savedPersonalInfo.recommendIntake){
                        Logger.d("exccededLiveData call\n일일 카페인 섭취량 : $intake \n저장되어 있는 권장섭취량 : ${savedPersonalInfo.recommendIntake}")

                        _exceededIntakeLiveData.postValue(null)
                    }
                }
            }, {
                stopLoadingIndicator()
            })
        )
    }

    fun setTotalCaffeineIntake(total : Int) {
        totalIntakeFromSplash = total
    }

    fun setSavedMenuList(list: ArrayList<Menu>){
        savedMenuList = list
    }

    fun getTotalCaffeineIntake(): Int = totalIntakeFromSplash

    fun getSavedMenuList(): ArrayList<Menu>? = savedMenuList
}