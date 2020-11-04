package com.example.musicplayer.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.musicplayer.model.AllSongsModel
import com.example.musicplayer.repository.AllSongsRepository

class AllSongsViewModel(application: Application) : AndroidViewModel(application) {
    val allsongsrepository = AllSongsRepository(application)


    fun get_list_of_all_songs():MutableLiveData<List<AllSongsModel>>{
        return allsongsrepository.allSongsList();
    }

}