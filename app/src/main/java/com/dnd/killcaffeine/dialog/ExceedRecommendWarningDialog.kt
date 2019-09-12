/*
 * Created by Lee Oh Hyoung on 2019-09-12..
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

class ExceedRecommendWarningDialog(context: Context) : Dialog(context) {

    init {
        setCancelable(true)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window?.run {
            setBackgroundDrawable(ColorDrawable(0))
        } ?: exitProcess(0)

        setContentView(R.layout.dialog_exceed_recommend_warning)

        // 경고 이미지 랜덤설정
        findViewById<ImageView>(R.id.dialog_exceed_recommend_warning_image_view).setImageResource(iconRandomSelection())
        findViewById<View>(R.id.dialog_exceed_recommend_warning_parent_view).setOnClickListener { cancel() }
    }

    private fun iconRandomSelection(): Int = when(Random.nextInt(3)) {
        0 -> R.drawable.icon_warning_1
        1 -> R.drawable.icon_warning_2
        2 -> R.drawable.icon_warning_3
        else -> R.drawable.icon_warning_1
    }
}