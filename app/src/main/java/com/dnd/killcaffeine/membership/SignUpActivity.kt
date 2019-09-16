/*
 * Created by Lee Oh Hyoung on 2019-09-16..
 */
package com.dnd.killcaffeine.membership

import android.widget.EditText
import androidx.core.content.ContextCompat
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivitySignUpBinding
import com.dnd.killcaffeine.utils.afterTextChanged
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {

    override val resourceId: Int
        get() = R.layout.activity_sign_up

    override val mViewModel: SignUpViewModel by viewModel()

    private var mSignUpButtonActivated = false

    override fun initViewStart() {
        setupKeyboardHide(view = activity_sign_up_constraint_parent_layout, activity = this)
    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {

        // 뒤로가기
        activity_sign_up_back_button.setOnClickListener {
            finish()
        }

        // 성함
        activity_sign_up_name_edit_text.afterTextChanged { s ->
            checkEditTextEmpty(s, activity_sign_up_email_edit_text, activity_sign_up_password_edit_text)
        }

        // 이메일
        activity_sign_up_email_edit_text.afterTextChanged { s ->
            checkEditTextEmpty(s, activity_sign_up_name_edit_text, activity_sign_up_password_edit_text)
        }

        // 비밀번호
        activity_sign_up_password_edit_text.afterTextChanged { s ->
            checkEditTextEmpty(s, activity_sign_up_name_edit_text, activity_sign_up_email_edit_text)
        }

        // 회원가입 버튼
        activity_sign_up_button.setOnClickListener {
            if(mSignUpButtonActivated){
                mViewModel.checkSignUpForm(
                    name = activity_sign_up_name_edit_text.text.toString(),
                    email = activity_sign_up_email_edit_text.text.toString(),
                    password = activity_sign_up_password_edit_text.text.toString()
                )
            }
        }
    }

    private fun changeSignUpButtonColor(resId: Int){
        activity_sign_up_button.background = ContextCompat.getDrawable(applicationContext, resId)
    }

    private fun checkEditTextEmpty(string: String, editText1: EditText, editText2: EditText){
        when(string.isEmpty()) {
            true -> {
                if(editText1.text.isEmpty() && editText2.text.isEmpty()){
                    changeSignUpButtonColor(R.drawable.background_radius_10dp_activity_sign_inactive_button)
                    mSignUpButtonActivated = false
                }
            }
            false -> {
                changeSignUpButtonColor(R.drawable.background_radius_10dp_activity_sign_active_button)
                mSignUpButtonActivated = true
            }
        }
    }
}