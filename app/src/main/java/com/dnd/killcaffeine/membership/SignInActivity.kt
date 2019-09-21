/*
 * Created by Lee Oh Hyoung on 2019-09-16 .. 
 */
package com.dnd.killcaffeine.membership

import android.content.Intent
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode.SIGN_UP_REQ_CODE
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivitySignInBinding
import com.dnd.killcaffeine.utils.afterTextChanged
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

        // 뒤로가기
        activity_sign_in_back_button.setOnClickListener {
            finish()
        }

        // 아이디
        activity_sign_in_id_edit_text.afterTextChanged { s ->
            checkEditTextEmpty(s, activity_sign_in_password_edit_text)
        }

        // 비밀번호
        activity_sign_in_password_edit_text.afterTextChanged { s ->
            checkEditTextEmpty(s, activity_sign_in_id_edit_text)
        }

        // 가입하기
        activity_sign_in_let_go_text_view.setOnClickListener {
            startActivityForResult(Intent(applicationContext, SignUpActivity::class.java), SIGN_UP_REQ_CODE)
        }

        // 로그인 버튼
        activity_sign_in_login_button.setOnClickListener {
            if(mLoginButtonActivated) {
                mViewModel.checkLoginForm(
                    id = activity_sign_in_id_edit_text.text.toString(),
                    password = activity_sign_in_password_edit_text.text.toString()
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK){
            when(requestCode){
                SIGN_UP_REQ_CODE -> {
                    showSnackbar("회원가입이 완료되었습니다")
                }
            }
        }
    }

    private fun changeLoginButtonColor(resId: Int){
        activity_sign_in_login_button.background = ContextCompat.getDrawable(applicationContext, resId)
    }

    private fun checkEditTextEmpty(string: String, editText: EditText){
        when(string.isEmpty()) {
            true -> {
                if(editText.text.isEmpty()){
                    changeLoginButtonColor(R.drawable.background_radius_10dp_activity_sign_inactive_button)
                    mLoginButtonActivated = false
                }
            }
            false -> {
                changeLoginButtonColor(R.drawable.background_radius_10dp_activity_sign_active_button)
                mLoginButtonActivated = true
            }
        }
    }
}