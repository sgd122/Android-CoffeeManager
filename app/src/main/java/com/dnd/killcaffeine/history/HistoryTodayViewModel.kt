/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.data.history.History
import com.dnd.killcaffeine.model.data.history.HistoryDatabase
import com.dnd.killcaffeine.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HistoryTodayViewModel : BaseViewModel() {

    private val _historyListLiveData = MutableLiveData<ArrayList<History>>()
    val historyListLiveData: LiveData<ArrayList<History>> get() = _historyListLiveData

    private val _insertHistoryLiveData = SingleLiveEvent<Any>()
    val insertHistoryLiveData: LiveData<Any> get() = _insertHistoryLiveData

    private val _failureHistoryLiveData = SingleLiveEvent<Any>()
    val failureHistoryLiveData: LiveData<Any> get() = _failureHistoryLiveData

    fun loadHistoryListFromRoomDatabase(historyDatabase: HistoryDatabase){
        addDisposable(historyDatabase.historyDao.getAllHistory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                _historyListLiveData.postValue(list as ArrayList<History>)
            }, {
                _failureHistoryLiveData.call()
            }))
    }

    fun insertHistoryListToRoomDatabase(historyList: List<History>, historyDatabase: HistoryDatabase){
        addDisposable(historyDatabase.historyDao.insertHistoryList(historyList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _insertHistoryLiveData.call()

            }, {

            })
        )
    }
}