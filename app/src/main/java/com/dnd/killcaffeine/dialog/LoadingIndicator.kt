/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
package com.dnd.killcaffeine.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.dnd.killcaffeine.R
import kotlinx.android.synthetic.main.dialog_loading_indicator.*
import kotlin.system.exitProcess

class LoadingIndicator(context: Context) : Dialog(context) {

    init {
        setCancelable(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window?.run {
            setBackgroundDrawable(ColorDrawable(0))
        } ?: exitProcess(0)

        setContentView(R.layout.dialog_loading_indicator)

        val frameAnimation: AnimationDrawable = dialog_loading_indicator_image_view.background as AnimationDrawable
        dialog_loading_indicator_image_view.post {
            frameAnimation.start()
        }
    }
}