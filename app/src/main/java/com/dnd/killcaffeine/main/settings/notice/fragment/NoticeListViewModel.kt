/*
 * Created by Lee Oh Hyoung on 2019-08-27..
 */
package com.dnd.killcaffeine.main.settings.notice.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.data.response.Notice
import com.dnd.killcaffeine.model.remote.CoffeeManagerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NoticeListViewModel : BaseViewModel(){

    private val _noticeListLiveData = MutableLiveData<ArrayList<Notice>>()
    val noticeListLiveData: LiveData<ArrayList<Notice>> get() = _noticeListLiveData

    fun getNoticeList(){
        addDisposable(CoffeeManagerService.getNoticeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                result?.run {
                    _noticeListLiveData.postValue(list)
                }
            }, {
                showSnackbar("공지사항을 불러오는데 실패했습니다.")
            }))
    }
}