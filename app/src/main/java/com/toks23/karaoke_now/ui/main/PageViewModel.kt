package com.toks23.karaoke_now.ui.main

import android.content.Context
import android.os.AsyncTask
import android.view.View
import android.view.animation.Transformation
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toks23.karaoke_now.R
import com.toks23.karaoke_now.model.SongList

class PageViewModel : ViewModel() {

   private val _index = MutableLiveData<Int>()
     val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    val index: LiveData<Int> = Transformations.map(_index) {
        it
    }

    /* val type: LiveData<ArrayList<SongList>> = Transformations.map(_index) {
        "Hello world from section: $it"
    }
    val title: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }
    val artist: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }*/

    fun setIndex(index: Int) {
        _index.value = index
    }

    private var _songList = MutableLiveData<List<List<SongList>>>()

    val songs : LiveData<List<SongList>> = Transformations.map(_index){

         _songList.value!![it]

    }

    fun loadSongs(list: MutableLiveData<List<List<SongList>>>){
        _songList = list
    }

   /* private val _songList: MutableLiveData<List<SongList>> by lazy {
        MutableLiveData<List<SongList>>().also {
            LoadSongsInBackGround().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
    }

    internal class LoadSongsInBackGround : AsyncTask<String, Void, List<SongList>>()
    {
        var dialogMsg : String = "Loading. Please wait..."
        var searchListLength: Int = 0
        lateinit var textView : TextView
        lateinit var pBar : LinearLayout

        override fun onPreExecute() {
            super.onPreExecute()

            pBar = R.id.progressbar as LinearLayout
            pBar.visibility = View.VISIBLE

        }

        override fun doInBackground(vararg p0: String?): List<SongList> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPostExecute(result: List<SongList>) {

            //setupRecyclerView(recyclerView, result)
        }

    }*/
}