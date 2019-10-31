package com.toks23.karaoke_now

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.toks23.karaoke_now.model.SongList

private lateinit var _songsList : MutableList<MutableList<SongList>>
private lateinit var _songsListByFilter :  MutableList<MutableList<SongList>>
private lateinit var _searchFor : String
private  var _searchInTabPage : Int = 0

var songCollections : MutableList<MutableList<SongList>>
    get() {
        return _songsList
    }
    set(value) {
        _songsList = value
    }

var songCollectionsByFilter :  MutableList<MutableList<SongList>>
    get() {
        return _songsListByFilter
    }
    set(value) {
        _songsListByFilter = value
    }

var searchFor : String?
    get() {
        return _searchFor
    }
    set(value) {
        _searchFor = value!!
    }

var searchInTabPage : Int
    get() {
        return _searchInTabPage
    }
    set(value) {
        _searchInTabPage = value
    }