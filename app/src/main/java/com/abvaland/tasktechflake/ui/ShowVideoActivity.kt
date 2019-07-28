package com.abvaland.tasktechflake.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.abvaland.tasktechflake.R
import com.abvaland.tasktechflake.base.Base
import com.abvaland.tasktechflake.model.MediaData
import com.abvaland.tasktechflake.objectbox.ObjectBox
import com.abvaland.tasktechflake.objectbox.VideoTab
import com.abvaland.tasktechflake.objectbox.VideoTab_
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import io.objectbox.BoxStore.context
import com.google.android.exoplayer2.util.Util.getUserAgent
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.MediaSource
import io.objectbox.Box
import io.objectbox.query.QueryBuilder




class ShowVideoActivity : Base() {
    @BindView(R.id.playerView)
    lateinit var  playerView: PlayerView

    @BindView(R.id.imgLike)
    lateinit var  imgLike: ImageView

    @BindView(R.id.imgDisLike)
    lateinit var  imgDisLike: ImageView

    @BindView(R.id.tvLikes)
    lateinit var  tvLikes: TextView

    @BindView(R.id.tvDisLikes)
    lateinit var  tvDisLikes: TextView

    var data : MediaData? = null
    lateinit var player : SimpleExoPlayer

    lateinit var videoBox : Box<VideoTab>
    var videoTab : VideoTab?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_video)
        ButterKnife.bind(this)

        getData()

        init()

        loadMedia()
    }

    private fun loadMedia() {
        val dataSourceFactory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, resources.getString(R.string.app_name))
        )
        val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(data?.images?.original?.mp4))
        player.prepare(videoSource)
    }

    private fun getData() {
        data = intent.getSerializableExtra("data") as MediaData?
    }

    private fun init() {
        player = ExoPlayerFactory.newSimpleInstance(this)
        playerView.player = player

        videoBox = ObjectBox.get()?.boxFor(VideoTab::class.java) as Box<VideoTab>
        videoTab = videoBox.query().equal(VideoTab_.videoId, data?.id).build().findFirst()

        updateUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
    @OnClick(R.id.imgLike,R.id.imgDisLike)
    fun OnClik(view : View){
        when(view.id){
            R.id.imgLike -> {
                upVote()
            }
            R.id.imgDisLike -> {
                downVote()
            }

        }
    }

    private fun downVote() {
        if(videoTab==null){
            var newVideoTab = VideoTab()
            newVideoTab.videoId = data?.id
            newVideoTab.upVote = 0
            newVideoTab.downVote = 1
            videoBox.put(newVideoTab)
            videoTab = newVideoTab
        }else{
            videoTab!!.downVote = videoTab!!.downVote+1
            videoBox.put(videoTab)
        }
        updateUI()
    }

    private fun upVote() {
        if(videoTab==null){
            var newVideoTab = VideoTab()
            newVideoTab.videoId = data?.id
            newVideoTab.upVote = 1
            newVideoTab.downVote = 0
            videoBox.put(newVideoTab)
            videoTab = newVideoTab
        }else{
            videoTab!!.upVote = videoTab!!.upVote+1
            videoBox.put(videoTab)
        }
        updateUI()
    }

    fun updateUI(){
        if(videoTab!=null){
            tvLikes.setText(""+ videoTab!!.upVote)
            tvDisLikes.setText(""+ videoTab!!.downVote)
        }
    }
}
