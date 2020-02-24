package com.toks23.karaoke_now
import android.widget.Filter
import com.toks23.karaoke_now.model.SongList
import com.toks23.karaoke_now.ui.main.RecyclerViewSongsAdapter

class CustomFilter(private var filteListsongs : List<SongList>, private var adapter : RecyclerViewSongsAdapter ) : Filter() {

    override fun performFiltering(p0: CharSequence?): FilterResults {
        val results = FilterResults()

        //CHECK CONSTRAINT VALIDITY
        if (p0 != null && p0.isNotEmpty()) { //CHANGE TO UPPER
            var  song = p0.toString().toUpperCase()
            //STORE OUR FILTERED PLAYERS
            val filteredSongs: ArrayList<SongList> = ArrayList()
            for (i in filteListsongs.indices) { //CHECK
                if (filteListsongs[i].songTitle.toUpperCase().contains(song)) { //ADD SONG TO FILTERED SONGS
                    filteredSongs.add(filteListsongs[i])
                }
            }
            results.count = filteredSongs.size
            results.values = filteredSongs
        } else {
            results.count = filteListsongs.size
            results.values = filteListsongs
        }

        return results
    }

    override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
        adapter.songs = p1?.values as ArrayList<SongList>

        //REFRESH
        adapter.notifyDataSetChanged()
    }
}