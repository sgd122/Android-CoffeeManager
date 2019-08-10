/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding, V: BaseViewModel> : Fragment() {

    abstract val resourceId: Int

    abstract val mViewModel: V
    private lateinit var mBinding: T

    abstract fun initViewStart()

    abstract fun initDataBinding()

    abstract fun initViewFinal()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        mBinding = DataBindingUtil.inflate(inflater, resourceId, container, false)
        mBinding.lifecycleOwner = this

        initViewStart()
        initDataBinding()
        initViewFinal()

        return mBinding.root
    }

    fun getFragmentBinding() = mBinding
}