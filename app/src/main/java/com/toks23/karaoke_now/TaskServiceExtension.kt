package com.toks23.karaoke_now

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.toks23.karaoke_now.model.SongList

private lateinit var songsList : ArrayList<ArrayList<SongList>>

var songCollections : ArrayList<ArrayList<SongList>>
    get() {
        return songsList
    }
    set(value) {
        songsList = value
    }