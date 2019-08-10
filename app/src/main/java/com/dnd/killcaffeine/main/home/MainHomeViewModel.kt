/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.data.result.DecaffeineResult
import com.dnd.killcaffeine.model.data.result.FranchiseResult
import com.dnd.killcaffeine.model.remote.CoffeeManagerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainHomeViewModel : BaseViewModel() {

    private val TAG = javaClass.name

    private val _decaffeineMenuLiveData = MutableLiveData<DecaffeineResult>()
    val decaffeineMenuLiveData: LiveData<DecaffeineResult> get() = _decaffeineMenuLiveData

    private val _franchiseMenuLiveData = MutableLiveData<FranchiseResult>()
    val franchiseMenuLiveData: LiveData<FranchiseResult> get() = _franchiseMenuLiveData


    fun getDecaffeineMenuList(){
        Log.d(TAG, "디카페인 진입")
        addDisposable(CoffeeManagerService.getDecaffineMenuList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _decaffeineMenuLiveData.postValue(result)

            }, {
                Log.d(TAG, it?.message ?: "")
            }))
    }

    fun getFranchiseMenuList(){
        addDisposable(CoffeeManagerService.getFranchiseMenuList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _franchiseMenuLiveData.postValue(result)

            }, {
                Log.d(TAG, it?.message ?: "")
            }))
    }
}