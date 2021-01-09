package com.target.targetcasestudy.app

import android.app.Application
import com.target.targetcasestudy.utils.Utility
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TargetApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Utility.setContext(this)
    }
}