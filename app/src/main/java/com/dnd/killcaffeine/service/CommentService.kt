/*
 * Created by Lee Oh Hyoung on 2019-09-10 .. 
 */
package com.dnd.killcaffeine.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import com.dnd.killcaffeine.R
import com.orhanobut.logger.Logger
import kotlin.random.Random

class CommentService : Service() {

    companion object {
        private const val COMMENT_CYCLE: Long = 1000 // 10초
    }

    inner class LocalBinder: Binder() {
        fun getService(): CommentService = this@CommentService
    }

    private val mBinder : IBinder = LocalBinder()
    private var mRandom: Int = 0
    private val mTodayComments: ArrayList<String> = ArrayList()

    override fun onBind(intent: Intent?): IBinder? {

        // TODO HomeFragment의 API와 Bind해줘야 됨.
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()

        Logger.d("CommentService 의 onCreate 실행중")

        mTodayComments.addAll(setupTodayComments())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val runnable = object: Runnable {
            override fun run() {
                mRandom = Random.nextInt(0, mTodayComments.size - 1)
                Handler().postDelayed(this, COMMENT_CYCLE)
            }
        }

        Handler().postDelayed(runnable, COMMENT_CYCLE)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        Logger.d("CommentService 의 onDestroy 실행중")
        stopSelf()
    }

    fun getRandomComment(): String = mTodayComments[mRandom]

    private fun setupTodayComments(): ArrayList<String> {
        return arrayListOf(
            getString(R.string.home_fragment_today_warning_comment_1),
            getString(R.string.home_fragment_today_warning_comment_2),
            getString(R.string.home_fragment_today_warning_comment_3),
            getString(R.string.home_fragment_today_warning_comment_4),
            getString(R.string.home_fragment_today_warning_comment_5)
        )
    }
}