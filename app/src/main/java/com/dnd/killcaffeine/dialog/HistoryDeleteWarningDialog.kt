/*
 * Created by Lee Oh Hyoung on 2019-09-03 .. 
 */
package com.dnd.killcaffeine.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Button
import com.dnd.killcaffeine.R
import kotlin.system.exitProcess

class HistoryDeleteWarningDialog(context: Context, onClickListener: View.OnClickListener? = null) : Dialog(context) {

    init {
        setCancelable(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window?.run {
            setBackgroundDrawable(ColorDrawable(0))
        } ?: exitProcess(0)

        setContentView(R.layout.dialog_history_delete_warning)

        findViewById<Button>(R.id.dialog_delete_warning_cancel_button).setOnClickListener { cancel() }
        findViewById<Button>(R.id.dialog_delete_warning_confirm_button).setOnClickListener { view ->
            onClickListener?.onClick(view)
            dismiss()
        }

    }
}