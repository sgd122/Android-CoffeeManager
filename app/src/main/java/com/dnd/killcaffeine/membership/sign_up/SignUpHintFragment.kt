/*
 * Created by Lee Oh Hyoung on 2019-09-20 .. 
 */
package com.dnd.killcaffeine.membership.sign_up

import android.app.Activity.RESULT_OK
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseFragment
import com.dnd.killcaffeine.databinding.FragmentSignUpHintBinding
import com.dnd.killcaffeine.utils.afterTextChanged
import kotlinx.android.synthetic.main.fragment_sign_up_basic.*
import kotlinx.android.synthetic.main.fragment_sign_up_hint.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpHintFragment: BaseFragment<FragmentSignUpHintBinding, SignUpHintViewModel>() {

    override val resourceId: Int
        get() = R.layout.fragment_sign_up_hint

    override val mViewModel: SignUpHintViewModel by viewModel()

    private var mSignUpDoneButtonActivated = false

    override fun initViewStart() {
        setupKeyboardHide(view = fragment_sign_up_hint_constraint_parent_layout, activity = activity)

    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {

        // 답
        fragment_sign_up_hin_answer_edit_text.afterTextChanged {
            checkEditTextEmpty(it)
        }

        // 회원가입 버튼
        fragment_sign_up_hint_done_button.setOnClickListener {
            activity?.run {
                setResult(RESULT_OK)
                finish()
            }
        }

    }

    private fun changeSignUpButtonColor(resId: Int){
        activity?.applicationContext?.let { ctx ->
            fragment_sign_up_hint_done_button.background = ContextCompat.getDrawable(ctx, resId)
        }
    }

    private fun checkEditTextEmpty(string: String){
        mSignUpDoneButtonActivated = when(string.isEmpty()) {
            true -> {
                changeSignUpButtonColor(R.drawable.background_radius_10dp_activity_sign_inactive_button)
                false
            }
            false -> {
                changeSignUpButtonColor(R.drawable.background_radius_10dp_activity_sign_active_button)
                true
            }
        }
    }
}