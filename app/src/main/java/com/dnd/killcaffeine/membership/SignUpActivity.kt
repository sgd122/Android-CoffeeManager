/*
 * Created by Lee Oh Hyoung on 2019-09-16..
 */
package com.dnd.killcaffeine.membership

import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseActivity
import com.dnd.killcaffeine.databinding.ActivitySignUpBinding
import com.dnd.killcaffeine.membership.sign_up.SignUpBasicFragment
import com.dnd.killcaffeine.membership.sign_up.SignUpHintFragment
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {

    enum class SignUp(val value: Int) {
        BASIC(0),
        PASSHINT(1)
    }

    override val resourceId: Int
        get() = R.layout.activity_sign_up

    override val mViewModel: SignUpViewModel by viewModel()

    private val containerResId: Int = R.id.activity_sign_up_container
    private val fragmentList = arrayListOf(
        SignUpBasicFragment(),
        SignUpHintFragment())

    override fun initViewStart() {
        supportFragmentManager.beginTransaction().replace(containerResId, fragmentList[SignUp.BASIC.value]).commit()

    }

    override fun initDataBinding() {
    }

    override fun initViewFinal() {

        // 뒤로가기
        activity_sign_up_back_button.setOnClickListener {
            finish()
        }
    }

    fun replacePassHintFragment(){
        supportFragmentManager.beginTransaction()
            .replace(containerResId, fragmentList[SignUp.PASSHINT.value])
            .addToBackStack(null)
            .commit()
    }

    fun replaceBasicFragment(){
        supportFragmentManager.beginTransaction()
            .replace(containerResId, fragmentList[SignUp.BASIC.value])
            .addToBackStack(null)
            .commit()
    }
}