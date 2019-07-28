package com.abvaland.tasktechflake.ui

import android.util.Log
import com.abvaland.tasktechflake.api.ApiService
import com.abvaland.tasktechflake.api.RetroClient
import com.abvaland.tasktechflake.base.BaseView
import com.abvaland.tasktechflake.model.VideoRes
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import kotlin.reflect.KFunction1
import com.abvaland.tasktechflake.ui.VideoView as VideoView1

class VideoPresenter(baseView: BaseView) {
    val TAG : String = "VideoPresenter"
    var apiService : ApiService?=null
    val apiKey : String = "Oatep4k234uvmKeVts0KNSn4MdDicvgA"
    val limit : Int = 10
    val offset : Int = 0
    val rating : String = "G"
    val lang : String = "en"
    var baseView : BaseView? = null
    var disposible : CompositeDisposable = CompositeDisposable()
    fun getView() : VideoView1 {
        return baseView as com.abvaland.tasktechflake.ui.VideoView
    }

    init {
        this.baseView = baseView;
        apiService = RetroClient.getClient().create(ApiService::class.java)
    }
    fun getVideos(query : String){
        getView().showLoading()
        apiService?.getVideos(apiKey,query,limit,offset,rating,lang)?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(this::handleResponse, this::handleError)?.let { disposible?.add(it) }
    }
    private fun handleResponse(res: Response<VideoRes>) {
        Log.e(TAG,"handleResponse() :"+res)
        getView().hideLoading()
        if(res.code()==200){
            getView().onResponse(res.body())
        }
    }
    private fun handleError(error: Throwable) {
        getView().hideLoading()
        getView().showMsg(error.toString())
        Log.e(TAG,"handleError() :"+error)
    }

     fun onDestroy() {
         disposible.clear()
    }
}