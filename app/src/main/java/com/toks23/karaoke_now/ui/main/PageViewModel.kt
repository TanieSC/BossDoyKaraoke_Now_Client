package com.toks23.karaoke_now.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.toks23.karaoke_now.model.SongList

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    private var _songList = MutableLiveData<MutableList<MutableList<SongList>>>()
    var _filteredSongs =  MutableLiveData<MutableList<MutableList<SongList>>>()
    var _oldFilteredSongs = MutableLiveData<MutableList<MutableList<SongList>>>()

    val index: LiveData<Int> = Transformations.map(_index) {
        it
    }

    val songs: LiveData<MutableList<MutableList<SongList>>> = Transformations.map(_songList) {
        it
    }

    //fun setIndex(index: Int) {
    //    _index.value = index
   // }

    fun loadSongs(setIndex: Int, songs :  MutableList<MutableList<SongList>>) {
        _index.value = setIndex
        _songList.value = songs
        _oldFilteredSongs.value = songs
    }


    fun search(text: String, selectedTab : Int){

        val filteredSongs : MutableList<SongList> = mutableListOf()

        val songs : List<SongList> = _songList.value!![selectedTab]

        for (eachSong in songs) {
            if (eachSong.songTitle!!.toLowerCase().contains(text.toLowerCase()) || eachSong.songArtist!!.toLowerCase().contains(text.toLowerCase())) {
                filteredSongs.add(eachSong)
            }
        }

        _songList.value!!.forEach { s -> s[selectedTab].songTitle.toLowerCase().contains(text.toLowerCase())}

        //_songList.value!![selectedTab].clear()
        //_songList.value!![selectedTab] = filteredSongs

        //return _songList.value!!

        //calling a method of the adapter class and passing the filtered list
        //_rva.filterList(filteredSongs)
        // setupRecyclerView(_recyclerView, filteredSongs)
    }
}