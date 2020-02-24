package com.toks23.karaoke_now.ui.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.toks23.karaoke_now.CustomFilter
import com.toks23.karaoke_now.R
import com.toks23.karaoke_now.model.SongList
import com.toks23.karaoke_now.searchInTabPage
import com.toks23.karaoke_now.songCollections
import kotlinx.android.synthetic.main.content_song_listitem.view.*

class RecyclerViewSongsAdapter(private val context: Context?, var songs : List<SongList>) : RecyclerView.Adapter<RecyclerViewSongsAdapter.ViewHolder>(), Filterable {

    private lateinit var animation: Animation

    private var _songList: List<SongList> = songs
    private var _songListFiltered: List<SongList> = songs
   // private var _filter : CustomFilter = CustomFilter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.content_song_listitem, parent, false))
    }

    override fun getItemCount(): Int {
        return _songListFiltered.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.songId.text = _songListFiltered[position].songId.toString()
        holder.songTitle.text = _songListFiltered[position].songTitle.toUpperCase()
        holder.songArtist.text = _songListFiltered[position].songArtist
        if(_songListFiltered[position].songFilename.toLowerCase().endsWith(".cdg"))
        {
            holder.songFileType.text = "MP3"
            holder.songFileType.setTextColor(ContextCompat.getColor(context!!, R.color.fileTypeBGmp3))
        }
        else if(_songListFiltered[position].songFilename.toLowerCase().endsWith(".mp4"))
        {
            holder.songFileType.text = "MP4"
            holder.songFileType.setTextColor(ContextCompat.getColor(context!!, R.color.fileTypeBGmp4))
        }
        holder.songfilePath.text = songs[position].songFilePath

        holder.itemView.setOnClickListener { v ->
            val song = _songListFiltered[position].songFilePath

            animation = AlphaAnimation(0.3f, 1.0f)
            animation.duration = 600
            v.startAnimation(animation)
        }
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                _songListFiltered = if (charString.isEmpty()) {
                    _songList
                } else {
                    val filteredList: MutableList<SongList> = ArrayList()
                    for (row in _songList) {
                        if (row.songTitle.toLowerCase().contains(charString.toLowerCase()) || row.songArtist.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }

                Log.d("SONGTITLE ", charString)

                val filterResults = FilterResults()
                filterResults.values = _songListFiltered
                filterResults.count = _songListFiltered.count()
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults)
            {
                _songListFiltered = filterResults.values as ArrayList<SongList>

                notifyDataSetChanged()
            }
        }
    }


    // override fun getFilter(): Filter {
       // if (_filter == null) {
        // }

   //     return CustomFilter(_filterList, this)
   // }

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val songId : TextView  = itemView.songId
        val songTitle : TextView = itemView.songTitle
        val songArtist : TextView = itemView.songArtist
        val songFileType : TextView = itemView.extension_type
        val songfilePath : TextView = itemView.songPath
    }

}