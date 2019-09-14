/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
package com.dnd.killcaffeine.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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

        Glide.with(context)
            .load(R.drawable.loading_indicator)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(dialog_loading_indicator_image_view)

    }
}