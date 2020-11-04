package com.example.musicplayer.repository

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.database.Cursor
import android.media.MediaPlayer
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.MediaController
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.musicplayer.model.AllSongsModel

class AllSongsRepository(application:Application) {


    private var context: Application
    var all_songs:MutableLiveData<List<AllSongsModel>> = MutableLiveData()
    var list_of_all_songs:ArrayList<AllSongsModel> = ArrayList<AllSongsModel>()


    lateinit var allSongsModel:AllSongsModel

    init {
        this.context = application;
    }



    fun allSongsList(): MutableLiveData<List<AllSongsModel>> {
        val cursor: Cursor? = context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null,null)
        if (cursor != null) {
            while (cursor.moveToNext()){
                var song_title:String =  cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                var song_duration:Int = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                var song_path:String = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                var song_artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                allSongsModel = AllSongsModel(song_title,song_duration,song_path,song_artist)
                list_of_all_songs.add(allSongsModel)

            }

            all_songs.value = list_of_all_songs
        }

        return  all_songs
    }

}