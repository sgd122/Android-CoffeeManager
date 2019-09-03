/*
 * Created by Lee Oh Hyoung on 2019-08-24..
 */
package com.dnd.killcaffeine.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.data.menu.Menu
import com.dnd.killcaffeine.model.data.menu.MenuDatabase
import com.dnd.killcaffeine.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HistoryTodayViewModel(private val mMenuDatabase: MenuDatabase) : BaseViewModel() {

    private val _historyListLiveData = MutableLiveData<ArrayList<Menu>>()
    val historyListLiveData: LiveData<ArrayList<Menu>> get() = _historyListLiveData

    private val _insertHistoryLiveData = SingleLiveEvent<Any>()
    val insertHistoryLiveData: LiveData<Any> get() = _insertHistoryLiveData

    private val _failureHistoryLiveData = SingleLiveEvent<Any>()
    val failureHistoryLiveData: LiveData<Any> get() = _failureHistoryLiveData

    private val _deleteHistoryLiveData = MutableLiveData<Menu>()
    val deleteHistoryLiveData: LiveData<Menu> get() = _deleteHistoryLiveData

    fun loadHistoryListFromRoomDatabase(){
        addDisposable(mMenuDatabase.menuDao.getAllMenu()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                _historyListLiveData.postValue(list as ArrayList<Menu>)
            }, {
                _failureHistoryLiveData.call()
            }))
    }

    fun insertHistoryToRoomDatabase(menu: Menu){
        addDisposable(mMenuDatabase.menuDao.insertMenu(menu)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _insertHistoryLiveData.call()

            }, {
                showSnackbar(it.message ?: "히스토리 등록에 실패하였습니다.")
            }))
    }

    fun insertHistoryListToRoomDatabase(menuList: List<Menu>){
        addDisposable(mMenuDatabase.menuDao.insertMenuList(menuList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _insertHistoryLiveData.call()

            }, {

            })
        )
    }

    fun deleteHistoryFromRoomDatabase(menu: Menu){
        addDisposable(mMenuDatabase.menuDao.deleteMenu(menu)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                _deleteHistoryLiveData.postValue(menu)

            }, {
                showSnackbar(it.message ?: "히스토리 삭제에 실패하였습니다.")
            }))
    }
}