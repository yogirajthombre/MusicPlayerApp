package com.example.musicplayer.adapter

import android.app.Application
import android.content.Intent
import android.media.MediaMetadataRetriever

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicplayer.R
import com.example.musicplayer.model.AllSongsModel
import com.example.musicplayer.view.PlaySongs
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class AllSongsAdapter(application: Application,all_song_names:ArrayList<AllSongsModel>): RecyclerView.Adapter<AllSongsAdapter.AllSongsViewHolder>() {
    private var context:Application
    private var byteArray: ByteArray? =null



    private var all_song_names: ArrayList<AllSongsModel>
        init {
            this.context = application
            this.all_song_names = all_song_names

        }

    class AllSongsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name_of_song:TextView
        var image_of_song:ImageView
        init {
            name_of_song = itemView.findViewById(R.id.name_of_Song)
            image_of_song = itemView.findViewById(R.id.image_of_song)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllSongsViewHolder {
       var View =   LayoutInflater.from(context).inflate(R.layout.song_item,parent,false)
        return AllSongsViewHolder(View)
    }

    override fun getItemCount(): Int {
        return all_song_names.size
    }

    override fun onBindViewHolder(holder: AllSongsViewHolder, position: Int) {
        holder.name_of_song.text = all_song_names.get(position).title

         get_album_photo(path = all_song_names[position].path)

        Glide.with(context).asBitmap().
            load(byteArray).
            into(holder.image_of_song)


        holder.itemView.setOnClickListener(View.OnClickListener {
           if (PlaySongs.mediaPlayer.isPlaying){
               PlaySongs.mediaPlayer.release()
           }
            var intent = Intent(context, PlaySongs::class.java)
            intent.putExtra("path_of_song",all_song_names[position].path)
            intent.putExtra("name_of_song",all_song_names[position].title)
            context.startActivity(intent)
        })
    }

    fun  get_album_photo(path:String) {

        GlobalScope.launch{
           var retriver = MediaMetadataRetriever()
           retriver.setDataSource(path)
             byteArray = retriver.embeddedPicture
            retriver.release()

       }





    }

}