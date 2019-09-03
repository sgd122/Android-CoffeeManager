/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.data.menu.Menu
import com.dnd.killcaffeine.model.data.menu.MenuDatabase
import com.dnd.killcaffeine.model.data.result.DecaffeineResult
import com.dnd.killcaffeine.model.remote.CoffeeManagerService
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainHomeViewModel(private val mMenuDatabase: MenuDatabase) : BaseViewModel() {

    private val _decaffeineMenuLiveData = MutableLiveData<DecaffeineResult>()
    val decaffeineMenuLiveData: LiveData<DecaffeineResult> get() = _decaffeineMenuLiveData

    private val _refreshedHistoryLiveData = MutableLiveData<ArrayList<Menu>>()
    val refreshedHistoryLiveData: LiveData<ArrayList<Menu>> get() = _refreshedHistoryLiveData

    private var totalIntakeFromSplash: Int = 0 // Splash 에서 넘겨받은 일일카페인 섭취량
    private var savedMenuList: ArrayList<Menu>? = null

    fun getDecaffeineMenuList(){
        addDisposable(CoffeeManagerService.getDecaffineMenuList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _decaffeineMenuLiveData.postValue(result)
                result.list.forEach {
                    Logger.d(it.toString())
                }

            }, {
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

    fun setTotalCaffeineIntake(total : Int) {
        totalIntakeFromSplash = total
    }

    fun setSavedMenuList(list: ArrayList<Menu>){
        savedMenuList = list
    }

    fun getTotalCaffeineIntake(): Int = totalIntakeFromSplash

    fun getSavedMenuList(): ArrayList<Menu>? = savedMenuList
}