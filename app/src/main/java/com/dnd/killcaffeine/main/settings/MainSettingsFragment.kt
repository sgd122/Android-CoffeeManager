/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.settings

import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainSettingsFragment : BaseFragment<FragmentSettingsBinding, MainSettingsViewModel>() {

    companion object {
        fun newInstance() = MainSettingsFragment()
    }

    override val mViewModel: MainSettingsViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_settings

    override fun initViewStart() {
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
   }
}