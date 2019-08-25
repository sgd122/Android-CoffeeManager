/*
 * Created by Lee Oh Hyoung on 2019-08-25..
 */
package com.dnd.killcaffeine.main.settings.terms

import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivitySettingsTermsBinding
import kotlinx.android.synthetic.main.activity_settings_terms.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainSettingsTermsActivity : BaseActivity<ActivitySettingsTermsBinding, MainSettingsTermsViewModel>(){

    override val resourceId: Int
        get() = R.layout.activity_settings_terms
    override val mViewModel: MainSettingsTermsViewModel by viewModel()

    override fun initViewStart() {
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
        activity_settings_terms_back_button.setOnClickListener {
            finish()
        }
    }
}