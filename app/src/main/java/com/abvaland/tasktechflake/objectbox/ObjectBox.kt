package com.abvaland.tasktechflake.objectbox

import android.content.Context
import io.objectbox.BoxStore

class ObjectBox {
    companion object{
        private var boxStore: BoxStore? = null
        fun init(context: Context) {
            boxStore = MyObjectBox.builder()
                .androidContext(context.applicationContext)
                .build()
        }

        fun get(): BoxStore? {
            return boxStore
        }
    }

}