package com.toks23.karaoke_now.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.toks23.karaoke_now.model.SongList
import io.reactivex.Completable

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    private var _songList = MutableLiveData<ArrayList<MutableList<SongList>>>()
    var _filteredSongs =  MutableLiveData<ArrayList<MutableList<SongList>>>()
    var _oldFilteredSongs = MutableLiveData<ArrayList<MutableList<SongList>>>()

    val index: LiveData<Int> = Transformations.map(_index) {
        it
    }

    val songs: LiveData<ArrayList<MutableList<SongList>>> = Transformations.map(_songList) {
            it
    }

    //fun setIndex(index: Int) {
    //    _index.value = index
   // }

    fun loadSongs(setIndex: Int, songs :  ArrayList<MutableList<SongList>>) {
        _index.value = setIndex
        _songList.value = songs
        _oldFilteredSongs.value = songs
        _filteredSongs.value = songs
    }
    fun search(query: String): Completable = Completable.create {

        _filteredSongs.value?.clear()

        for(i in 1.._oldFilteredSongs.value!!.size)
        {
           val filtered = _oldFilteredSongs.value!![i-1].filter { data ->

               data.songTitle.contains(query) || data.songArtist.contains(query)

           }.toList()

           // Log.d("_oldFilteredSongs : ", filtered[i-1].songTitle)

            _filteredSongs.value!![i-1].addAll(filtered)
            it.onComplete()
        }

    }
}