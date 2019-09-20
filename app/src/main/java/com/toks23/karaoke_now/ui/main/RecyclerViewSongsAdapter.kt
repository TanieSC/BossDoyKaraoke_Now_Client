package com.toks23.karaoke_now.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.toks23.karaoke_now.MainActivity
import com.toks23.karaoke_now.R
import com.toks23.karaoke_now.model.SongList
import kotlinx.android.synthetic.main.content_song_listitem.view.*

class RecyclerViewSongsAdapter(private val context: Context?, private val songs : List<SongList>) : RecyclerView.Adapter<RecyclerViewSongsAdapter.ViewHolder>() {

    private lateinit var animation: Animation

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.content_song_listitem, parent, false))
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.songId.text = songs[position].songId.toString()
        holder.songTitle.text = songs[position].songTitle.toUpperCase()
        holder.songArtist.text = songs[position].songArtist
        if(songs[position].songFilename.toLowerCase().endsWith(".cdg"))
        {
            holder.songFileType.text = "MP3"
            holder.songFileType.setTextColor(ContextCompat.getColor(context!!, R.color.fileTypeBGmp3))
        }
        else if(songs[position].songFilename.toLowerCase().endsWith(".mp4"))
        {
            holder.songFileType.text = "MP4"
            holder.songFileType.setTextColor(ContextCompat.getColor(context!!, R.color.fileTypeBGmp4))
        }
        holder.songfilePath.text = songs[position].songFilePath

        holder.itemView.setOnClickListener { v ->
            val song = songs[position].songFilePath

            animation = AlphaAnimation(0.3f, 1.0f)
            animation.duration = 600
            v.startAnimation(animation)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val songId : TextView  = itemView.songId
        val songTitle : TextView = itemView.songTitle
        val songArtist : TextView = itemView.songArtist
        val songFileType : TextView = itemView.extension_type
        val songfilePath : TextView = itemView.songPath
    }
}