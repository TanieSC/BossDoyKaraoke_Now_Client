package com.toks23.karaoke_now.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    //val text: LiveData<String> = Transformations.map(_index) {
    //    "Hello world from section: $it"
    //}
    val type: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }
    val title: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }
    val artist: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}