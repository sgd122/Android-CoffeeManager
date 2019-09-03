/*
 * Created by Lee Oh Hyoung on 2019-09-03..
 */
package com.dnd.killcaffeine.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.ImageView
import com.dnd.killcaffeine.R
import kotlin.random.Random
import kotlin.system.exitProcess

class HistoryRegisterWarningDialog(context: Context, onClickListener: View.OnClickListener? = null) : Dialog(context) {

    private var mOnClickListener: View.OnClickListener? = null

    init {
        setCancelable(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window?.run {
            setBackgroundDrawable(ColorDrawable(0))
        } ?: exitProcess(0)

        setContentView(R.layout.dialog_history_register_warning)

        mOnClickListener = onClickListener

        // 다이얼로그 클릭시 사라짐
        findViewById<View>(R.id.dialog_warning_parent_layout).setOnClickListener { view ->
            mOnClickListener?.onClick(view)
            dismiss()
        }

        // 경고 이미지 랜덤설정
        findViewById<ImageView>(R.id.dialog_warning_image_view).setImageResource(iconRandomSelection())
    }

    private fun iconRandomSelection(): Int = when(Random.nextInt(3)) {
        0 -> R.drawable.icon_warning_1
        1 -> R.drawable.icon_warning_2
        2 -> R.drawable.icon_warning_3
        else -> R.drawable.icon_warning_1
    }
}