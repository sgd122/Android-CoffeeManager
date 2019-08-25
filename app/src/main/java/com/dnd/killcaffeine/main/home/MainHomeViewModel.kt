/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.data.result.DecaffeineResult
import com.dnd.killcaffeine.model.data.result.FranchiseResult
import com.dnd.killcaffeine.model.remote.CoffeeManagerService
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainHomeViewModel : BaseViewModel() {

    private val _decaffeineMenuLiveData = MutableLiveData<DecaffeineResult>()
    val decaffeineMenuLiveData: LiveData<DecaffeineResult> get() = _decaffeineMenuLiveData

    private val _franchiseMenuLiveData = MutableLiveData<FranchiseResult>()
    val franchiseMenuLiveData: LiveData<FranchiseResult> get() = _franchiseMenuLiveData


    fun getDecaffeineMenuList(){
        Logger.d("디카페인 진입")
        addDisposable(CoffeeManagerService.getDecaffineMenuList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _decaffeineMenuLiveData.postValue(result)
                result.list.forEach {
                    Logger.d(it.toString())
                }

            }, {
                Logger.d("디카페인 가져오기 실패")
                Logger.d(it?.message ?: "")
            }))
    }

    fun getFranchiseMenuList(){
        addDisposable(CoffeeManagerService.getFranchiseMenuList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _franchiseMenuLiveData.postValue(result)

            }, {
                Logger.d(it?.message ?: "")
            }))
    }
}