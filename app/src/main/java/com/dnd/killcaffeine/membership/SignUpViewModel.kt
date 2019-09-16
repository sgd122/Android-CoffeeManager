/*
 * Created by Lee Oh Hyoung on 2019-09-16..
 */
package com.dnd.killcaffeine.membership

import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.utils.RegexUtil

class SignUpViewModel : BaseViewModel() {

    fun checkSignUpForm(name:String?, email: String?, password: String?): Boolean {

        when {
            name.isNullOrEmpty() -> {
                showSnackbar("이름을 입력해주세요.")
                return false

            }
            !name.matches(RegexUtil.NAME) -> {
                showSnackbar("한글, 영문을 포함하여\n4~13자리 이름을 입력해주세요.")
                return false

            }
            email.isNullOrEmpty() -> {
                showSnackbar("이메일을 입력해주세요.")
                return false

            }
            !email.matches(RegexUtil.EMAIL) -> {
                showSnackbar("이메일 형식이 올바르지 않습니다.")
                return false

            }
            password.isNullOrEmpty() -> {
                showSnackbar("비밀번호가 올바르지 않습니다.")
                return false

            }
            !password.matches(RegexUtil.PASSWORD) -> {
                showSnackbar("숫자, 영문을 포함하여\n4~13자리 비밀번호를 입력해주세요.")
                return false

            }
            else -> {
                showSnackbar("준비중인 기능입니다.")
                return true
            }
        }
    }
}