/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainHomeFragment : BaseFragment<FragmentHomeBinding, MainHomeViewModel>() {

    companion object {
        fun newInstance() = MainHomeFragment()
    }

    override val mViewModel: MainHomeViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_home

    override fun initViewStart() {
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
    }
}