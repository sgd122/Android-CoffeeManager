/*
 * Created by Lee Oh Hyoung on 2019-09-03..
 */
package com.dnd.killcaffeine.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.TextView
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.model.data.menu.Menu
import kotlin.system.exitProcess

class RecentDrinkDetailDialog(context: Context, menu: Menu) : Dialog(context) {

    init {
        setCancelable(true)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window?.run {
            setBackgroundDrawable(ColorDrawable(0))
        } ?: exitProcess(0)

        setContentView(R.layout.dialog_recent_drink_detail)

        // 뷰를 누르면 다이얼로그 Cancel
        findViewById<View>(R.id.dialog_recent_drink_detail_parent_layout).setOnClickListener {
            cancel()
        }

        findViewById<TextView>(R.id.dialog_recent_drink_detail_menu_name).text = menu.menuName
        findViewById<TextView>(R.id.dialog_recent_drink_detail_franchise_name).text = menu.franchiseName
        findViewById<TextView>(R.id.dialog_recent_drink_detail_caffeine).text = context.getString(R.string.dialog_recent_drink_caffeine_form, menu.caffeine.toString())
    }
}