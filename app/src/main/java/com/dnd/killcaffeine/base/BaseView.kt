package com.dnd.killcaffeine.base

import android.app.Activity
import android.content.Context
import android.view.View
import com.dnd.killcaffeine.R
import kotlinx.android.synthetic.main.toolbar.view.*

/*
 * Created by iohyeong on 2019-08-15..
 */
interface BaseView {

    fun setToolbar(resourceId: Int?, title: String? = "커피 매니저")

    fun setupKeyboardHide(view: View, activity: Activity?)

    fun loadingIndicatorObserving()

    fun showNotImplementToast(applicationContext: Context)
}