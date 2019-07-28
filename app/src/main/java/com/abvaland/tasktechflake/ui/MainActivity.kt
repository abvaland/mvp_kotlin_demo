package com.abvaland.tasktechflake.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.abvaland.tasktechflake.model.MediaData
import com.abvaland.tasktechflake.R
import com.abvaland.tasktechflake.base.Base
import com.abvaland.tasktechflake.base.BaseView
import com.abvaland.tasktechflake.model.BaseRes
import com.abvaland.tasktechflake.model.VideoRes

class MainActivity : Base(),VideoView{
    val TAG : String = "MainActivity"
    override fun onResponse(response: BaseRes?) {
        var videoRes : VideoRes = response as VideoRes
        Log.e(TAG,"res : "+videoRes)
        list.addAll(videoRes.data)
        mediAdapter?.notifyDataSetChanged()
    }


    @BindView(R.id.rvVideos)
    lateinit var  rvVides: RecyclerView
    @BindView(R.id.etSearch)
    lateinit var  etSearch: EditText
    @BindView(R.id.imgSearch)
    lateinit var  imgSearch: ImageView
    var presenter : VideoPresenter?=null
    val list : ArrayList<MediaData> = ArrayList()
    var mediAdapter: MediaAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)

        init()
    }
    private fun init() {
        mediAdapter = MediaAdapter(list)
        rvVides?.adapter = mediAdapter

        presenter = VideoPresenter(this)
    }
    @OnClick(R.id.imgSearch)
    fun onClick(view : View){
        if(!TextUtils.isEmpty(etSearch.text.toString())){
            list.clear()
            mediAdapter?.notifyDataSetChanged()
            presenter?.getVideos(etSearch.text.toString())
        }else{
            showMsg(resources.getString(R.string.hint_search))
        }
    }
}
