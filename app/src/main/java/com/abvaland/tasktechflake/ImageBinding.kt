package com.abvaland.tasktechflake

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object ImageBinding {
    private val TAG = "ImageBinding"


    fun loadImage(view: ImageView, imageUrl: String,w : Int,h : Int) {
        Log.e(TAG, "loadImage : $imageUrl")
        val defaultImage = view.context.resources.getDrawable(R.drawable.placeholder)
        val requestOption =
            RequestOptions().placeholder(defaultImage).override(w,h).error(defaultImage).diskCacheStrategy(DiskCacheStrategy.ALL)
        if (!TextUtils.isEmpty(imageUrl))
            Glide.with(view.context).load(imageUrl).apply(requestOption).thumbnail(0.1f).into(view)
        else
            view.setImageResource(R.drawable.placeholder)
    }
}
