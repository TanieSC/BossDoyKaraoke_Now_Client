package com.toks23.karaoke_now.command.service

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Handler
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.toks23.karaoke_now.MainActivity
import com.toks23.karaoke_now.R
import com.toks23.karaoke_now.model.SongList
import com.toks23.karaoke_now.songCollections
import com.toks23.karaoke_now.songCollectionsByFilter
import com.toks23.karaoke_now.ui.main.PageViewModel
import com.toks23.karaoke_now.ui.main.RecyclerViewSongsAdapter
import java.io.*

class BackGroundTaskService(private val activity: Activity?, private val loadSongsByFilter : Int) : AsyncTask<String, Void, MutableList<MutableList<SongList>>>() {

    private val SPLASH_SCREEN_TIME_OUT = 2000L
    private val _constants = ConstantsService()
    private lateinit var _splashScreenIntent : Intent

    override fun onPreExecute() {

        when(loadSongsByFilter){
            _constants.GET_ALL_SONGS -> {
                _splashScreenIntent = Intent(
                    activity,
                    MainActivity::class.java
                )
            }
        }
    }

    override fun doInBackground(vararg p0: String?): MutableList<MutableList<SongList>>? {
        var songs : ArrayList<ArrayList<SongList>>? = ArrayList()

         return when(loadSongsByFilter) {
             _constants.GET_ALL_SONGS -> {
                 copyFileFromAssets()
             }
             _constants.SEARCH_SONGS_TITLE -> {
                 songCollectionsByFilter
             }
             _constants.SEARCH_SONGS_ARTIST -> {
                 songCollectionsByFilter
             }
             else -> songCollections
         }

       // return songs
    }

    override fun onPostExecute(result: MutableList<MutableList<SongList>>) {

        when(loadSongsByFilter){
            _constants.GET_ALL_SONGS -> {
                songCollections = result

                Handler().postDelayed({
                    activity?.startActivity(_splashScreenIntent)
                    activity?.finish()
                }, SPLASH_SCREEN_TIME_OUT)
            }
            else -> songCollections
        }
    }

    private fun copyFileFromAssets() : MutableList<MutableList<SongList>>? {

        val myInput =  activity?.assets?.open("Toks.bkN")
        val audioFilter = object : FilenameFilter {
            lateinit var f: File

            override fun accept(dir: File, name: String): Boolean {
                if (name.toLowerCase().endsWith(".bkn")) {
                    return true
                }
                f = File(dir.absolutePath + "/" + name)
                return f.isDirectory
            }
        }
        val path = activity?.filesDir?.list(audioFilter)

        if (path!!.isEmpty() || path != null) {

            val fos = activity?.openFileOutput("Toks.bkN", Context.MODE_PRIVATE)
            val buffer = ByteArray(1024)
            var length = 0

            while ({length = myInput!!.read(buffer); length}() > 0) {
                fos?.write(buffer, 0, length)
            }
            myInput?.close()
            fos?.flush()
            fos?.close()
        } else {

            // Log.i("FileExist", "True");
        }

       return generateSongList(".bkN")
    }

    private fun generateSongList(file_extension: String): MutableList<MutableList<SongList>>? {
        val songsViewModelList = mutableListOf<MutableList<SongList>>(
            mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()
            , mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()
            , mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())

        try {
            var s: String? = null
            val allsongs = activity?.filesDir?.listFiles { pathname -> pathname.path.endsWith(file_extension) }
            var count = 1
            if (allsongs != null) {
                for (i in allsongs.indices) {
                    val fis = activity?.openFileInput(allsongs?.get(i)?.name)
                    val br = BufferedReader(InputStreamReader(fis))

                    while ({s = br.readLine(); s}() != null) {
                        try {
                            if (s?.substring(s!!.lastIndexOf("."))?.trim()!!.isNotEmpty()) {
                                val songList = SongList()
                                val song = s!!.substring(s!!.lastIndexOf("\\") + 1, s!!.length - 4).trim()
                                val title = song.lastIndexOf(" - ")

                                songList.songId = count++
                                if(title == -1) {
                                    songList.songTitle = song
                                }
                                else{
                                    songList.songTitle = song.substring( title + 2).trim()
                                }
                                songList.songArtist = song
                                songList.songFilename = s as String
                                songList.songFilePath = s as String

                                when(songList.songTitle[0].toLowerCase().toString())
                                {
                                    "a" -> { songsViewModelList[1].add(songList) }
                                    "b" -> { songsViewModelList[2].add(songList) }
                                    "c" -> { songsViewModelList[3].add(songList) }
                                    "d" -> { songsViewModelList[4].add(songList) }
                                    "e" -> { songsViewModelList[5].add(songList) }
                                    "f" -> { songsViewModelList[6].add(songList) }
                                    "g" -> { songsViewModelList[7].add(songList) }
                                    "h" -> { songsViewModelList[8].add(songList) }
                                    "i" -> { songsViewModelList[9].add(songList) }
                                    "j" -> { songsViewModelList[10].add(songList) }
                                    "k" -> { songsViewModelList[11].add(songList) }
                                    "l" -> { songsViewModelList[12].add(songList) }
                                    "m" -> { songsViewModelList[13].add(songList) }
                                    "n" -> { songsViewModelList[14].add(songList) }
                                    "o" -> { songsViewModelList[15].add(songList) }
                                    "p" -> { songsViewModelList[16].add(songList) }
                                    "q" -> { songsViewModelList[17].add(songList) }
                                    "r" -> { songsViewModelList[18].add(songList) }
                                    "s" -> { songsViewModelList[19].add(songList) }
                                    "t" -> { songsViewModelList[20].add(songList) }
                                    "u" -> { songsViewModelList[21].add(songList) }
                                    "v" -> { songsViewModelList[22].add(songList) }
                                    "w" -> { songsViewModelList[23].add(songList) }
                                    "x" -> { songsViewModelList[24].add(songList) }
                                    "y" -> { songsViewModelList[25].add(songList) }
                                    "z" -> { songsViewModelList[26].add(songList) }
                                    else -> { songsViewModelList[0].add(songList) }
                                }

                            }

                        } catch (iode: StringIndexOutOfBoundsException) {

                        }
                    }
                    br.close()
                }
            }

             for(i in 1..songsViewModelList.size)
             {
                 songsViewModelList[i-1].sortWith(Comparator { obj1, obj2 ->
                     // ## Ascending order
                     obj1.songTitle.compareTo(obj2.songTitle, true)
                 })
             }

            return songsViewModelList

        } catch (e: IOException) {
            e.printStackTrace()
            Log.i("Data :", "FILE - false")
            return null
        }

    }
}