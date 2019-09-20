/*
 * Created by Lee Oh Hyoung on 2019-09-18 .. 
 */
package com.dnd.killcaffeine.membership.sign_up

import android.widget.EditText
import androidx.core.content.ContextCompat
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentSignUpBasicBinding
import com.dnd.killcaffeine.membership.SignUpActivity
import com.dnd.killcaffeine.utils.afterTextChanged
import kotlinx.android.synthetic.main.fragment_sign_up_basic.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpBasicFragment : BaseFragment<FragmentSignUpBasicBinding, SignUpBasicViewModel>() {

    override val resourceId: Int
        get() = R.layout.fragment_sign_up_basic

    override val mViewModel: SignUpBasicViewModel by viewModel()

    private var mSignUpNextButtonActivated = false

    override fun initViewStart() {
        setupKeyboardHide(view = fragment_sign_up_basic_constraint_parent_layout, activity = activity)


    }

    override fun initDataBinding() {

    }

    override fun initViewFinal() {

        // 성함
        fragment_sign_up_basic_id_edit_text.afterTextChanged { s ->
            checkEditTextEmpty(s, fragment_sign_up_basic_password_edit_text)
        }

        // 비밀번호
        fragment_sign_up_basic_password_edit_text.afterTextChanged { s ->
            checkEditTextEmpty(s, fragment_sign_up_basic_id_edit_text)
        }

        // 다음 버튼
        fragment_sign_up_basic_next_button.setOnClickListener {
            if(mSignUpNextButtonActivated){
                val checkedForm:Boolean = mViewModel.checkSignUpForm(
                    id = fragment_sign_up_basic_id_edit_text.text.toString(),
                    password = fragment_sign_up_basic_password_edit_text.text.toString()
                )
                if(checkedForm){
                    activity?.let {
                        val signUpActivity: SignUpActivity = it as SignUpActivity
                        signUpActivity.replacePassHintFragment()
                    }
                }
            }
        }
    }

    private fun changeSignUpButtonColor(resId: Int){
        activity?.applicationContext?.let { ctx ->
            fragment_sign_up_basic_next_button.background = ContextCompat.getDrawable(ctx, resId)
        }
    }

    private fun checkEditTextEmpty(string: String, editText: EditText){
        when(string.isEmpty()) {
            true -> {
                if(editText.text.isEmpty()){
                    changeSignUpButtonColor(R.drawable.background_radius_10dp_activity_sign_inactive_button)
                    mSignUpNextButtonActivated = false
                }
            }
            false -> {
                changeSignUpButtonColor(R.drawable.background_radius_10dp_activity_sign_active_button)
                mSignUpNextButtonActivated = true
            }
        }
    }
}