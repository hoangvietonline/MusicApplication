package com.example.musicapplication

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.musicapplication.common.Constant
import hoangviet.ndhv.demoui.model.Music
import kotlinx.android.synthetic.main.activity_play_music.*
import java.text.SimpleDateFormat
import java.util.*

class PlayMusicActivity : AppCompatActivity() {
    private lateinit var mMediaPlayer: MediaPlayer
    val format = SimpleDateFormat("mm:ss", Locale.getDefault())
    private val mHandler = Handler()
    private val mRunnable = object : Runnable {
        override fun run() {
            seeBarPlayMusic.progress = mMediaPlayer.currentPosition
            mHandler.postDelayed(this, 500)
            txtTimeRun.text = format.format(mMediaPlayer.currentPosition)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_music)
        val music = intent.getParcelableExtra<Music>(Constant.MUSIC_KEY)
        if (music != null) {
            Glide.with(this).load(music.musicImage).into(imgAvatarPlayMusic)
            txtNamePlayMusic.text = music.musicName
            txtSingerPlayMusic.text = music.musicSinger
            mMediaPlayer = MediaPlayer.create(this, music.fileSong!!)
            mMediaPlayer.start()
            checkBtnPlayMusic(mMediaPlayer.isPlaying)
            seeBarPlayMusic.max = mMediaPlayer.duration
            mHandler.postDelayed(mRunnable, 100)
        }
        btnPlayPlayMusic.setOnClickListener {
            playAndPause()
            checkBtnPlayMusic(mMediaPlayer.isPlaying)
        }
        btnNextPlayMusic.setOnClickListener {
            mMediaPlayer.seekTo(mMediaPlayer.currentPosition + 10000)
        }
        btnPreviousPlayMusic.setOnClickListener {
            mMediaPlayer.seekTo(mMediaPlayer.currentPosition - 10000)
        }
        seeBarPlayMusic.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                mMediaPlayer.seekTo(seekBar.progress)
            }
        })
        txtTimeSum.text = format.format(mMediaPlayer.duration)
    }

    private fun checkBtnPlayMusic(isPlay: Boolean) {
        if (isPlay)
            btnPlayPlayMusic.setImageDrawable(getDrawable(R.drawable.ic_pause))
        else
            btnPlayPlayMusic.setImageDrawable(getDrawable(R.drawable.ic_play_button))

    }

    private fun playAndPause() {
        if (mMediaPlayer.isPlaying)
            mMediaPlayer.pause()
        else
            mMediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer.stop()
        mMediaPlayer.release()
        mHandler.removeCallbacks(mRunnable)
    }
}