/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val mCompositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable?) = disposable?.let { mCompositeDisposable.add(it) }
}