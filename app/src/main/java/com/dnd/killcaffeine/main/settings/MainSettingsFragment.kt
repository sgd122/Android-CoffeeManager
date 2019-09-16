/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main.settings

import android.content.Intent
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentSettingsBinding
import com.dnd.killcaffeine.main.settings.notice.MainSettingsNoticeActivity
import com.dnd.killcaffeine.main.settings.personal.MainPersonalSettingActivity
import com.dnd.killcaffeine.main.settings.terms.MainSettingsTermsActivity
import com.dnd.killcaffeine.membership.SignInActivity
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainSettingsFragment : BaseFragment<FragmentSettingsBinding, MainSettingsViewModel>() {

    override val mViewModel: MainSettingsViewModel by viewModel()
    override val resourceId: Int
        get() = R.layout.fragment_settings

    override fun initViewStart() {
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {

        // 로그인 버튼
        fragment_settings_login_button.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, SignInActivity::class.java))
        }

        // 로그아웃 버튼
        fragment_settings_logout_button.setOnClickListener {

        }

        // 마이카페인 설정
        fragment_settings_set_personal_button.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, MainPersonalSettingActivity::class.java))
        }

        // 공지사항
        fragment_settings_notice_button.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, MainSettingsNoticeActivity::class.java))
        }

        // 이용약관
        fragment_settings_terms_button.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, MainSettingsTermsActivity::class.java))
        }
   }
}