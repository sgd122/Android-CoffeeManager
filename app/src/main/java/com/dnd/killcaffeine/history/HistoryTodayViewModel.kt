/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.CoffeeRepository
import com.dnd.killcaffeine.model.data.room.menu.Menu
import com.dnd.killcaffeine.model.data.room.menu.MenuDatabase
import com.dnd.killcaffeine.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HistoryTodayViewModel(private val mRepository: CoffeeRepository) : BaseViewModel() {

    private val _historyListLiveData = MutableLiveData<ArrayList<Menu>>()
    val historyListLiveData: LiveData<ArrayList<Menu>> get() = _historyListLiveData

    private val _insertHistoryLiveData = SingleLiveEvent<Any>()
    val insertHistoryLiveData: LiveData<Any> get() = _insertHistoryLiveData

    private val _deleteHistoryLiveData = MutableLiveData<Menu>()
    val deleteHistoryLiveData: LiveData<Menu> get() = _deleteHistoryLiveData

    private val _deleteAllHistoryLiveData = SingleLiveEvent<Any>()
    val deleteAllHistoryLiveData: LiveData<Any> get() = _deleteAllHistoryLiveData

    fun loadHistoryListFromRoomDatabase(){
        startLoadingIndicator()
        addDisposable(mRepository.getAllMenu()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                stopLoadingIndicator()
                _historyListLiveData.postValue(list as ArrayList<Menu>)
            }, {
                stopLoadingIndicator()
                _historyListLiveData.postValue(ArrayList())
            }))
    }

    fun insertHistoryToRoomDatabase(menu: Menu){
        startLoadingIndicator()
        addDisposable(mRepository.insertMenu(menu)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                stopLoadingIndicator()
                _insertHistoryLiveData.call()

            }, {
                stopLoadingIndicator()
                showSnackbar(it.message ?: "히스토리 등록에 실패하였습니다.")
            }))
    }

    fun insertHistoryListToRoomDatabase(menuList: List<Menu>){
        addDisposable(mRepository.insertMenuList(menuList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _insertHistoryLiveData.call()

            }, {

            })
        )
    }

    fun deleteHistoryFromRoomDatabase(menu: Menu){
        addDisposable(mRepository.deleteMenu(menu)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                _deleteHistoryLiveData.postValue(menu)

            }, {
                showSnackbar(it.message ?: "히스토리 삭제에 실패하였습니다.")
            }))
    }

    fun deleteAllHistory() {
        startLoadingIndicator()
        addDisposable(mRepository.deleteAllMenu()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                stopLoadingIndicator()
                _deleteAllHistoryLiveData.call()
            }, {
                stopLoadingIndicator()
                showSnackbar("히스토리 비우기 실패 했습니다")
            })
        )
    }
}