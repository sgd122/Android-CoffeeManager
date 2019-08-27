/*
 * Created by Lee Oh Hyoung on 2019-07-30 .. 
 */
package com.dnd.killcaffeine.main

import com.dnd.killcaffeine.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    private var pressedTime: Long = 0

    fun onBackPressed(): Boolean{
        return when(System.currentTimeMillis() > pressedTime + 2000){
            true -> {
                pressedTime = System.currentTimeMillis()
                showSnackbar("뒤로가기를 한번 더 누르시면 종료됩니다.")
                false
            }
            false -> {
                true
            }
        }
    }
}