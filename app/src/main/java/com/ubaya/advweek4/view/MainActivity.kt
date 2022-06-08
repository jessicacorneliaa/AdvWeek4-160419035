package com.ubaya.advweek4.view

import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ubaya.advweek4.R
import com.ubaya.advweek4.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    init {
        instance= this
    }
    companion object{
        private var instance:MainActivity ?= null
        fun showNotification(title:String, content:String, icon:Int){
            val channelId= "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val builder= NotificationCompat.Builder(instance!!.applicationContext, channelId)
                .apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority= NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }
            val notif= NotificationManagerCompat.from(instance!!.applicationContext)
            notif.notify(1001, builder.build())

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, true, getString(R.string.app_name), "AppChannel")

        Observable.just("a stream of data", "hellow", "world")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                // onNext
                {Log.d("Messages","Data captured: ${it}")},
                // onError
                {Log.e("Messages","Error: ${it.message.toString()}")},
                // onComplete
                {Log.d("Messages","Completed")}
            )
    }
}