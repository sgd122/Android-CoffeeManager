/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.statistics

import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentStatisticsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainStatisticsFragment : BaseFragment<FragmentStatisticsBinding, MainStatisticsViewModel>() {

    override val mViewModel: MainStatisticsViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_statistics

    override fun initViewStart() {
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
    }
}