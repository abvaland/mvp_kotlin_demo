package com.abvaland.tasktechflake.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.abvaland.tasktechflake.ImageBinding
import com.abvaland.tasktechflake.model.MediaData
import com.abvaland.tasktechflake.R
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

class MediaAdapter(val items: ArrayList<MediaData>) : RecyclerView.Adapter<MediaAdapter.ViewHoler>() {
    var context : Context ?= null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        context = parent.context
        return ViewHoler(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_media,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        Log.e("Adapter","ddd")
        ImageBinding.loadImage(holder.ImgMedia,items.get(position).images.original.mp4,
            items.get(position).images.original.width,
                    items.get(position).images.original.height)
    }

    inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {
        @BindView(R.id.imgMedia)
        lateinit var  ImgMedia : ImageView
        init {
            ButterKnife.bind(this, view)
        }
        @OnClick(R.id.imgMedia)
        fun onCLik(view : View){
            var intent = Intent(context,ShowVideoActivity::class.java)
            intent.putExtra("data",items.get(adapterPosition))
            context?.startActivity(intent)
        }
    }

}