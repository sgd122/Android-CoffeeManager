/*
 * Created by Lee Oh Hyoung on 2019-09-24..
 */
package com.dnd.killcaffeine.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.RequestCode
import com.dnd.killcaffeine.main.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.orhanobut.logger.Logger

class CoffeeManagerFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // 포그라운드에서만 동작함.
        // val firebaseFrom: String = remoteMessage.from ?: resources.getString(R.string.firebase_message_not_received)
        //val message: String = data["message"] ?: resources.getString(R.string.firebase_message_not_received)

        val data: Map<String, String> = remoteMessage.data
        val notification = remoteMessage.notification

        notification?.let { noti ->
            sendNotification(noti, data)
        }

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Logger.d("파이어 베이스 토큰 onNewToken : $token")
    }

    private fun sendNotification(notification: RemoteMessage.Notification, data: Map<String, String>){

        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                RequestCode.FIREBASE_CHANNEL_ID,
                RequestCode.FIREBASE_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = RequestCode.FIREBASE_CHANNEL_DESCRIPTION
                enableVibration(true)
                enableLights(true)
                lightColor = Color.RED
            }

            notificationManager.createNotificationChannel(channel)
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext,
            RequestCode.FIREBASE_PEDING_INTENT_REQ_CODE,
            Intent(applicationContext, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT)

        val action = NotificationCompat.Action.Builder(R.mipmap.app_icon, RequestCode.FIREBASE_NOTIFICATION_ACTION, pendingIntent)
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.app_icon)

        val builder = NotificationCompat.Builder(applicationContext, RequestCode.FIREBASE_CHANNEL_ID).apply {
            setSmallIcon(R.mipmap.app_icon)
            setLargeIcon(largeIcon)
            setWhen(System.currentTimeMillis())
            setContentTitle(notification.title)
            setContentText(notification.body)
            setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
            setAutoCancel(true)
            setContentIntent(pendingIntent)
            addAction(action.build())
            color = ContextCompat.getColor(applicationContext, R.color.colorAccent)
        }

        notificationManager.notify(RequestCode.FIREBASE_NOTIFICATION_ID, builder.build())
    }

}