package com.toks23.karaoke_now.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.toks23.karaoke_now.CustomFilter
import com.toks23.karaoke_now.model.SongList
import kotlinx.android.synthetic.main.content_song_listitem.view.*

class RecyclerViewSongsAdapter0 : RecyclerView.Adapter<RecyclerViewSongsAdapter0.ViewHolder>(), Filterable {

    lateinit var _context : Context
    lateinit var _filteredList : ArrayList<SongList>
    lateinit var _customFilter : CustomFilter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFilter(): Filter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val songId : TextView = itemView.songId
        val songTitle : TextView = itemView.songTitle
        val songArtist : TextView = itemView.songArtist
        val songFileType : TextView = itemView.extension_type
        val songfilePath : TextView = itemView.songPath
    }
}