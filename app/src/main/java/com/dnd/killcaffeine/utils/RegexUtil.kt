package com.dnd.killcaffeine.utils

/*
 * Created by Lee Oh Hyoung on 2019-09-16..
 */
object RegexUtil {

    val NAME: Regex = Regex("^[가-힣a-zA-z]{2,13}$")
    val EMAIL: Regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    val PASSWORD: Regex = Regex("^(?=.*[0-9])(?=.*[a-z]).{4,13}$")

    // Upper case
    //(?=.*[A-Z])
}