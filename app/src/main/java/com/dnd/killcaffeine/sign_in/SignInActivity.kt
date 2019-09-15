/*
 * Created by Lee Oh Hyoung on 2019-09-16 .. 
 */
package com.dnd.killcaffeine.sign_in

import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivitySignInBinding
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : BaseActivity<ActivitySignInBinding, SignInViewModel>() {

    override val resourceId: Int
        get() = R.layout.activity_sign_in

    override val mViewModel: SignInViewModel by viewModel()

    override fun initViewStart() {
        activity_sign_in_back_button.setOnClickListener {
            finish()
        }
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {
    }

}