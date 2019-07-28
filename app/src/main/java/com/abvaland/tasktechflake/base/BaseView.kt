package com.abvaland.tasktechflake.base


interface BaseView {

    fun isOnline(): Boolean
    fun showLoading()

    fun hideLoading()

    fun showMsg(msg: String)

}
