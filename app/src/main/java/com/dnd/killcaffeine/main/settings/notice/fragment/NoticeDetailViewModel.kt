/*
 * Created by Lee Oh Hyoung on 2019-08-27..
 */
package com.dnd.killcaffeine.main.settings.notice.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.CoffeeRepository
import com.dnd.killcaffeine.model.remote.service.CoffeeManagerService
import com.dnd.killcaffeine.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NoticeDetailViewModel(private val mCoffeeRepository: CoffeeRepository) : BaseViewModel() {

    private val _noticeDetailLiveData = MutableLiveData<String>()
    val noticeDetailLiveData: LiveData<String> get() = _noticeDetailLiveData

    private val _exceptionNoticeLiveData = SingleLiveEvent<Any>()
    val exceptionNoticeLiveData: LiveData<Any> get() = _exceptionNoticeLiveData

    fun getNoticeDetail(noticeId: Int){
        startLoadingIndicator()
        addDisposable(mCoffeeRepository.getNoticeDetail(noticeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ detail ->
                stopLoadingIndicator()
                detail?.run {
                    _noticeDetailLiveData.postValue(data)

                }
            }, {
                stopLoadingIndicator()
                showSnackbar("공지사항을 불러오는데 실패했습니다.")
                _exceptionNoticeLiveData.call()
            }))
    }
}