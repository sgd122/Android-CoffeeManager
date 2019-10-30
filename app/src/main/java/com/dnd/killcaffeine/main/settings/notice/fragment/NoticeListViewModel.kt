/*
 * Created by Lee Oh Hyoung on 2019-08-27..
 */
package com.dnd.killcaffeine.main.settings.notice.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.CoffeeRepository
import com.dnd.killcaffeine.model.data.response.Notice
import com.dnd.killcaffeine.model.remote.service.CoffeeManagerService
import com.dnd.killcaffeine.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NoticeListViewModel(private val mCoffeeRepository: CoffeeRepository) : BaseViewModel(){

    private val _noticeListLiveData = MutableLiveData<ArrayList<Notice>>()
    val noticeListLiveData: LiveData<ArrayList<Notice>> get() = _noticeListLiveData

    /*private val _noticeErrorLiveData = SingleLiveEvent<Any>()
    val noticeErrorLiveData: LiveData<Any> get() = _noticeErrorLiveData*/

    val noticeErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getNoticeList(){
        startLoadingIndicator()
        addDisposable(mCoffeeRepository.getNoticeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                stopLoadingIndicator()
                result?.run {
                    noticeErrorLiveData.value = false
                    _noticeListLiveData.postValue(list)
                }
            }, {
                stopLoadingIndicator()
                noticeErrorLiveData.value = true
                showSnackbar("공지사항을 불러오는데 실패했습니다.")
            }))
    }
}