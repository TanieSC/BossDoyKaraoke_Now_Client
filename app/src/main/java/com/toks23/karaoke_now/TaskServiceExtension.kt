package com.toks23.karaoke_now

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.toks23.karaoke_now.model.SongList

private lateinit var _songsList : ArrayList<MutableList<SongList>>
private lateinit var _songsListByFilter : ArrayList<MutableList<SongList>>

var songCollections : ArrayList<MutableList<SongList>>
    get() {
        return _songsList
    }
    set(value) {
        _songsList = value
    }

var songCollectionsByFilter : ArrayList<MutableList<SongList>>
    get() {
        return _songsListByFilter
    }
    set(value) {
        _songsListByFilter = value
    }