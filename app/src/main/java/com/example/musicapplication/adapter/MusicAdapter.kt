package com.example.musicapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapplication.R
import hoangviet.ndhv.demoui.model.Music


class MusicAdapter constructor(private val mContext: Context) :
    RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    private var mOnClickItemMusicListener: OnItemClickListener? = null
    private  var mMusicList =  mutableListOf<Music>()

    fun addMusicList(musicList: MutableList<Music>) {
        if (mMusicList.size > 0) mMusicList.clear()
        mMusicList = musicList
        notifyDataSetChanged()
    }

    fun setOnClickItemMusicListener(mOnClickItemMusicListener: OnItemClickListener?) {
        this.mOnClickItemMusicListener = mOnClickItemMusicListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MusicViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.line_music, viewGroup, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, i: Int) {
        val music = mMusicList[i]
        holder.txtMusicName.text = music.musicName
        holder.txtMusicSinger.text = music.musicSinger
        Glide.with(mContext).load(music.musicImage).into(holder.imgMusicAvatar)
        holder.itemView.setOnClickListener { mOnClickItemMusicListener!!.onclickItem(i) }
    }

    override fun getItemCount(): Int {
        return mMusicList.size
    }

    interface OnItemClickListener {
        fun onclickItem(position: Int)
    }

    inner class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtMusicName: TextView = itemView.findViewById(R.id.txtMusicName)
        val txtMusicSinger: TextView = itemView.findViewById(R.id.txtMusicSinger)
        val imgMusicAvatar: ImageView = itemView.findViewById(R.id.imgAvatarMusic)
    }
}