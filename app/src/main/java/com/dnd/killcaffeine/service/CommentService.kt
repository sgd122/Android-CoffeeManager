/*
 * Created by Lee Oh Hyoung on 2019-09-10 .. 
 */
package com.dnd.killcaffeine.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import com.dnd.killcaffeine.BroadcastReceiverKey
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.main.home.MainHomeFragment
import com.orhanobut.logger.Logger
import java.lang.ref.WeakReference
import kotlin.random.Random

class CommentService : Service() {

    companion object {
        private const val COMMENT_CYCLE: Long = 30000 // 10초
    }

    private var mRandom: Int = 0
    private val mCommentHandler by lazy { Handler() }
    private val mTodayComments by lazy { ArrayList<String>() }

    override fun onBind(intent: Intent?): IBinder? = throw UnsupportedOperationException("Not Yet Implemented")

    override fun onCreate() {
        super.onCreate()

        mTodayComments.addAll(setupTodayComments())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        setupHandlerWork()

        return START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()

        Logger.d("CommentService 의 onDestroy 호출")

        mCommentHandler.removeCallbacksAndMessages(null)
        stopSelf()
    }

    private fun setupHandlerWork(){
        val runnable = object: Runnable {
            override fun run() {
                mRandom = Random.nextInt(from = 0, until = mTodayComments.size)
                sendCommentBroadcast()

                mCommentHandler.postDelayed(this, COMMENT_CYCLE)
            }
        }

        mCommentHandler.postDelayed(runnable, COMMENT_CYCLE)
    }

    private fun sendCommentBroadcast(){
        val intent = Intent(BroadcastReceiverKey.COMMENT_SERVICE_BROADCAST).apply {
            putExtra(BroadcastReceiverKey.COMMENT_SERVICE_RANDOM, getRandomComment())
        }

        sendBroadcast(intent)
    }

    private fun getRandomComment(): String = mTodayComments[mRandom]

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