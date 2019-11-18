/*
 * Created by Lee Oh Hyoung on 2019-08-25..
 */
package com.dnd.killcaffeine.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.room.Room
import com.dnd.killcaffeine.main.MainActivity
import com.dnd.killcaffeine.model.data.room.menu.MenuDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ActionDateChangedReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "알람"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if(context != null && intent != null) {
            clearRoomDatabase(context)
        }
    }

    private fun clearRoomDatabase(context: Context) {
        val room = Room.databaseBuilder(
            context.applicationContext,
            MenuDatabase::class.java,
            "Menu-db").build()

        CompositeDisposable().add(room.menuDao.deleteAllMenu()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "테이블 모든 Rows 초기화 완료")

                // 메인 액티비티 재 실행
                context.startActivity(Intent(context.applicationContext, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                })

            }, {
                Log.d(TAG, "테이블 Delete 실패")
            })
        )

    }
}