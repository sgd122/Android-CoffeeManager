/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val _snackbarLiveData = MutableLiveData<String>()
    val snackbarLiveData: LiveData<String> get() = _snackbarLiveData

    private val _snackbarResIdLiveData = MutableLiveData<Int>()
    val snackbarResIdLiveData: LiveData<Int> get() = _snackbarResIdLiveData

    private val mCompositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable?) = disposable?.let { mCompositeDisposable.add(it) }

    fun showSnackbar(description: String){
        _snackbarLiveData.postValue(description)
    }

    fun showSnackbar(stringResId: Int){
        _snackbarResIdLiveData.postValue(stringResId)
    }
}