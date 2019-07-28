package com.abvaland.tasktechflake.model

import com.google.gson.Gson

open class BaseRes{
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
