package com.abvaland.tasktechflake.base

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abvaland.tasktechflake.R

open class Base : AppCompatActivity(){
    var pb : ProgressBar?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pb = findViewById(R.id.pb)
    }
    fun showLoading() {
        pb?.visibility = View.VISIBLE
    }

    fun hideLoading() {
        pb?.visibility = View.GONE
    }
    fun isOnline(): Boolean{
            val connectivity = getSystemService(
                CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                val info = connectivity.allNetworkInfo
                if (info != null)
                    for (i in info)
                        if (i.state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
            }
            return false
        }


    fun showMsg(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}