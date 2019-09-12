package com.toks23.karaoke_now.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.toks23.karaoke_now.model.SongList
import com.toks23.karaoke_now.songCollections

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    private var _songList = MutableLiveData<ArrayList<ArrayList<SongList>>>()

    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    val index: LiveData<Int> = Transformations.map(_index) {
        it
    }

    val songs: LiveData<ArrayList<ArrayList<SongList>>> = Transformations.map(_songList) {
            it
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun loadSongs(songs :  ArrayList<ArrayList<SongList>>) {
        _songList.value = songs
    }
}