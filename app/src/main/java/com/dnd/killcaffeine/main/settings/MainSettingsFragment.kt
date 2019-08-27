/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.settings

import android.content.Intent
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentSettingsBinding
import com.dnd.killcaffeine.main.settings.notice.MainSettingsNoticeActivity
import com.dnd.killcaffeine.main.settings.terms.MainSettingsTermsActivity
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

        // 공지사항
        getFragmentBinding().fragmentSettingsNoticeButton.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, MainSettingsNoticeActivity::class.java))
        }

        // 이용약관
        getFragmentBinding().fragmentSettingsTermsButton.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, MainSettingsTermsActivity::class.java))
        }
   }
}