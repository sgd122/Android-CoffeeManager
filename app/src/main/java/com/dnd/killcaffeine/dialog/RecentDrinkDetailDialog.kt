/*
 * Created by Lee Oh Hyoung on 2019-09-03..
 */
package com.dnd.killcaffeine.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.data.room.menu.Menu
import kotlin.system.exitProcess

class RecentDrinkDetailDialog(context: Context, menu: Menu, onRegisterListener: OnRecentDrinkRegisterListener) : Dialog(context) {

    interface OnRecentDrinkRegisterListener {
        fun onRecentDrinkRegister(menu: Menu)
    }

    init {
        setCancelable(true)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window?.run {
            setBackgroundDrawable(ColorDrawable(0))
        } ?: exitProcess(0)

        setContentView(R.layout.dialog_recent_drink_detail)

        // 음료 사진
        findViewById<ImageView>(R.id.dialog_recent_drink_detail_image_view).load(menu.menuImgUrl) {
            crossfade(true)
            placeholder(R.drawable.background_radius_10dp_white_box)
            error(R.drawable.background_radius_10dp_white_box)
        }

        // 돌아가기
        findViewById<Button>(R.id.dialog_recent_drink_cancel_button).setOnClickListener {
            cancel()
        }

        // 추가하기
        findViewById<Button>(R.id.dialog_recent_drink_confirm_button).setOnClickListener {
            onRegisterListener.onRecentDrinkRegister(menu = menu)
            dismiss()
        }

        findViewById<TextView>(R.id.dialog_recent_drink_detail_menu_name).text = menu.menuName
        findViewById<TextView>(R.id.dialog_recent_drink_detail_franchise_name).text = menu.franchiseName

        with(context.getString(R.string.dialog_recent_drink_caffeine_form, menu.caffeine.toString())) {
            findViewById<TextView>(R.id.dialog_recent_drink_detail_caffeine).text = this
            findViewById<TextView>(R.id.dialog_recent_drink_detail_caffeine_indicator).text = this
        }
    }
}