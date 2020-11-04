package com.example.musicplayer.view

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TokenWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import com.example.musicplayer.R

class PlaySongs : AppCompatActivity() {

companion object PlaySong {
    var mediaPlayer:MediaPlayer = MediaPlayer()
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_songs)

        var intent = intent
        var name_of_song:TextView = findViewById(R.id.tv_name_of_song)
        name_of_song.text = intent.getStringExtra("name_of_song")

        var play_or_pause_button:ImageView = findViewById(R.id.iv_play_or_pause_song)

        start_song()


        play_or_pause_button.setOnClickListener(View.OnClickListener {
            if (mediaPlayer?.isPlaying!!){
                mediaPlayer?.pause()
                play_or_pause_button.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }else {
                play_or_pause_button.setImageResource(R.drawable.ic_baseline_pause_24)
                mediaPlayer!!.start()
            }
        })



    }

    fun start_song(){
        var path_of_song = intent.getStringExtra("path_of_song")
        mediaPlayer = MediaPlayer.create(applicationContext, Uri.parse(path_of_song))
        mediaPlayer?.start()

    }

}


