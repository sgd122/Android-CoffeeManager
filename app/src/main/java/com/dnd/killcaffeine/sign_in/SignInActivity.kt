/*
 * Created by Lee Oh Hyoung on 2019-09-16 .. 
 */
package com.dnd.killcaffeine.sign_in

import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivitySignInBinding
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : BaseActivity<ActivitySignInBinding, SignInViewModel>() {

    override val resourceId: Int
        get() = R.layout.activity_sign_in

    override val mViewModel: SignInViewModel by viewModel()

    private var mLoginButtonActivated = false

    override fun initViewStart() {
        setupKeyboardHide(view = activity_sign_in_constraint_parent_layout, activity = this)

    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {

        activity_sign_in_back_button.setOnClickListener {
            finish()
        }

        activity_sign_in_login_button.setOnClickListener {
            if(mLoginButtonActivated) {
                mViewModel.checkLoginForm(
                    email = activity_sign_in_email_edit_text.text.toString(),
                    password = activity_sign_in_password_edit_text.text.toString()
                )
            }
        }

        activity_sign_in_email_edit_text.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable) {
                when(s.toString().isEmpty()) {
                    true -> {
                        if(activity_sign_in_password_edit_text.text.isEmpty()){
                            changeLoginButtonColor(R.drawable.background_radius_10dp_activity_sign_in_inactive_button)
                            mLoginButtonActivated = false
                        }
                    }
                    false -> {
                        changeLoginButtonColor(R.drawable.background_radius_10dp_activity_sign_in_active_button)
                        mLoginButtonActivated = true
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        })

        activity_sign_in_password_edit_text.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable) {
                when(s.toString().isEmpty()) {
                    true -> {
                        if(activity_sign_in_email_edit_text.text.isEmpty()){
                            changeLoginButtonColor(R.drawable.background_radius_10dp_activity_sign_in_inactive_button)
                            mLoginButtonActivated = false
                        }
                    }
                    false -> {
                        changeLoginButtonColor(R.drawable.background_radius_10dp_activity_sign_in_active_button)
                        mLoginButtonActivated = true
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        })
    }

    private fun changeLoginButtonColor(resId: Int){
        activity_sign_in_login_button.background = ContextCompat.getDrawable(applicationContext, resId)
    }
}