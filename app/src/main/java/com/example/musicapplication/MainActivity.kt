package com.example.musicapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapplication.adapter.MusicAdapter
import com.example.musicapplication.common.Constant
import hoangviet.ndhv.demoui.model.Music
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MusicAdapter.OnItemClickListener {
    private var musicList = mutableListOf<Music>()
    private lateinit var mAdapter: MusicAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = MusicAdapter(this)
        rcViewMusic.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }
        mAdapter.setOnClickItemMusicListener(this)
        addMusic()
    }

    private fun addMusic() {
        musicList.add(Music("Bạc Phận", "K-ICM ft. JACK", "https://cdn.tuoitre.vn/thumb_w/640/2019/6/19/jack-1560931851558668237008.jpg", R.raw.bac_phan))
        musicList.add(Music("Đừng nói tôi điên", "Hiền Hồ", "https://vcdn-ione.vnecdn.net/2018/12/13/43623062-928967060639978-82410-4074-2366-1544693013.png", R.raw.dung_noi_toi_dien))
        musicList.add(Music("Em ngày xưa khác rồi", "Hiền Hồ", "https://vcdn-ione.vnecdn.net/2018/12/13/43623062-928967060639978-82410-4074-2366-1544693013.png", R.raw.em_ngay_xua_khac_roi))
        musicList.add(Music("Hồng Nhan", "Jack", "https://kenh14cdn.com/zoom/700_438/2019/4/16/520385336113309193300413143308017856937984n-15554316885891494708426-crop-15554316943631888232929.jpg", R.raw.hong_nhan_jack))
        musicList.add(Music("Mây và núi", "The Bells", "https://www.pngkey.com/png/detail/129-1296419_cartoon-mountains-png-mountain-animation-png.png", R.raw.may_va_nui))
        musicList.add(Music("Rồi người thương cũng hóa người dưng", "Hiền Hồ", "https://vcdn-ione.vnecdn.net/2018/12/13/43623062-928967060639978-82410-4074-2366-1544693013.png", R.raw.roi_nguoi_thuong_cung_hoa_nguoi_dung))
        mAdapter.addMusicList(musicList)
    }

    override fun onclickItem(position: Int) {
        val intent = Intent(this, PlayMusicActivity::class.java)
        intent.putExtra(Constant.MUSIC_KEY, musicList[position])
        startActivity(intent)
    }
}