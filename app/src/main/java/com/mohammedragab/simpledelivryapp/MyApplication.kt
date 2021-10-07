package com.mohammedragab.simpledelivryapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.mohammedragab.simpledelivryapp.di.ApplicationComponent
import com.mohammedragab.simpledelivryapp.di.DaggerApplicationComponent


class MyApplication : Application() {
    val CHANNEL_ID = "exampleChannel"
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
    override fun onCreate() {
        super.onCreate()

    }

}