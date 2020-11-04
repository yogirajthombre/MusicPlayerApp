package com.example.musicplayer.view

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.adapter.AllSongsAdapter
import com.example.musicplayer.model.AllSongsModel
import com.example.musicplayer.viewmodel.AllSongsViewModel
import java.util.jar.Manifest

class ListofAllSongs : AppCompatActivity() {

    lateinit var allSongsViewModel:AllSongsViewModel
    lateinit var recyclerView:RecyclerView
    lateinit var all_songs_adapter:AllSongsAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.all_songs_list)

        allSongsViewModel = ViewModelProviders.of(this).get(AllSongsViewModel::class.java)

        if (check_for_permissions()){
            allSongsViewModel.get_list_of_all_songs().observe(this, Observer {
                if (!it.isEmpty()){
                    all_songs_adapter = AllSongsAdapter(applicationContext as Application,
                        it as ArrayList<AllSongsModel>
                    )
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                    var divider_item_decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
                    recyclerView.addItemDecoration(divider_item_decoration)
                    recyclerView.adapter = all_songs_adapter

                }
            })

        }else {
            request_permission()

        }

    }

    fun check_for_permissions(): Boolean {
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED){
            return true
        }else {
            return false
        }
    }

    fun request_permission(){
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),100)
    }
}


