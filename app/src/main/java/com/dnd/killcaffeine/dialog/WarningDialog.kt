/*
 * Created by Lee Oh Hyoung on 2019-09-03..
 */
package com.dnd.killcaffeine.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import com.dnd.killcaffeine.R
import kotlin.system.exitProcess

class WarningDialog(context: Context, onClickListener: View.OnClickListener? = null) : Dialog(context) {

    init {
        setCancelable(true)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window?.run {
            setBackgroundDrawable(ColorDrawable(0))
        } ?: exitProcess(0)

        setContentView(R.layout.dialog_caffeine_warning)
        findViewById<View>(R.id.dialog_warning_parent_layout).setOnClickListener {
            cancel()
        }
    }
}