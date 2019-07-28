package com.abvaland.tasktechflake

import android.app.Application
import com.abvaland.tasktechflake.objectbox.ObjectBox

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}
