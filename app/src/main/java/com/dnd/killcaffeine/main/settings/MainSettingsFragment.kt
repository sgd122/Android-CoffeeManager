/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.settings

import android.content.Intent
import android.view.View
import com.dnd.killcaffeine.BR
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentSettingsBinding
import com.dnd.killcaffeine.main.settings.notice.MainSettingsNoticeActivity
import com.dnd.killcaffeine.main.settings.personal.MainPersonalSettingActivity
import com.dnd.killcaffeine.main.settings.terms.MainSettingsTermsActivity
import com.dnd.killcaffeine.membership.SignInActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainSettingsFragment : BaseFragment<FragmentSettingsBinding, MainSettingsViewModel>() {

    override val mViewModel: MainSettingsViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_settings

    override fun initViewStart() {

        getFragmentBinding().setVariable(BR.fragment, this)
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {

    }

    fun clickButton(view: View?){
        view?.let {
            when(it.id) {
                R.id.fragment_settings_login_button -> startActivity(Intent(activity?.applicationContext, SignInActivity::class.java))

                R.id.fragment_settings_logout_button -> {}

                R.id.fragment_settings_set_personal_button -> startActivity(Intent(activity?.applicationContext, MainPersonalSettingActivity::class.java))

                R.id.fragment_settings_notice_button -> startActivity(Intent(activity?.applicationContext, MainSettingsNoticeActivity::class.java))

                R.id.fragment_settings_terms_button -> startActivity(Intent(activity?.applicationContext, MainSettingsTermsActivity::class.java))
            }
        }
    }
}