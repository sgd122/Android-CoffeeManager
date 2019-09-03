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

class HistoryTodayViewModel(private val mHistoryDatabase: HistoryDatabase) : BaseViewModel() {

    private val _historyListLiveData = MutableLiveData<ArrayList<History>>()
    val historyListLiveData: LiveData<ArrayList<History>> get() = _historyListLiveData

    private val _insertHistoryLiveData = SingleLiveEvent<Any>()
    val insertHistoryLiveData: LiveData<Any> get() = _insertHistoryLiveData

    private val _failureHistoryLiveData = SingleLiveEvent<Any>()
    val failureHistoryLiveData: LiveData<Any> get() = _failureHistoryLiveData

    private val _deleteHistoryLiveData = MutableLiveData<History>()
    val deleteHistoryLiveData: LiveData<History> get() = _deleteHistoryLiveData

    fun loadHistoryListFromRoomDatabase(){
        addDisposable(mHistoryDatabase.historyDao.getAllHistory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                _historyListLiveData.postValue(list as ArrayList<History>)
            }, {
                _failureHistoryLiveData.call()
            }))
    }

    fun insertHistoryToRoomDatabase(history: History){
        addDisposable(mHistoryDatabase.historyDao.insertHistory(history)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _insertHistoryLiveData.call()

            }, {
                showSnackbar(it.message ?: "히스토리 등록에 실패하였습니다.")
            }))
    }

    fun insertHistoryListToRoomDatabase(historyList: List<History>){
        addDisposable(mHistoryDatabase.historyDao.insertHistoryList(historyList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _insertHistoryLiveData.call()

            }, {

            })
        )
    }

    fun deleteHistoryFromRoomDatabase(history: History){
        addDisposable(mHistoryDatabase.historyDao.deleteHistory(history)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                _deleteHistoryLiveData.postValue(history)

            }, {
                showSnackbar(it.message ?: "히스토리 삭제에 실패하였습니다.")
            }))
    }
}