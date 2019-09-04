package com.toks23.karaoke_now.ui.main

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.toks23.karaoke_now.R
import com.toks23.karaoke_now.model.SongList
import java.io.*
//import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment(), SearchView.OnQueryTextListener, AdapterView.OnItemClickListener  {

   lateinit var recyclerView : RecyclerView

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 0)
       }

       // pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
           // loadSongs()
      //  }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        //pageViewModel.text.observe(this, Observer<String> {
        //    textView.text = it
        //})

        pageViewModel.index.observe(this, Observer<Int> {

            recyclerView = root.findViewById(R.id.songList) as RecyclerView
            LoadSongsInBackGround(context)

        })
           /* when (it) {
                0 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                1 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                2 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                3 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                4 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                5 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                6 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                7 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                8 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                9 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                10 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                11 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                12 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                13 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                14 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                15 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                16 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                17 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                18 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                19 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                20 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                21 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                22 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                23 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                24 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                25 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }
                26 -> {
                    pageViewModel.text.observe(this, Observer<String> { its ->
                        textView.text = its
                    })
                    //return true
                }

            }
        })*/


        return root
    }

    private fun generateSongList(file_extension: String): ArrayList<ArrayList<SongList>>? {
        val songs : ArrayList<SongList> = ArrayList()
        val songsViewModelList : ArrayList<ArrayList<SongList>> = ArrayList(27)

        try {
            var s: String? = null
            val allsongs = activity?.filesDir?.listFiles { pathname -> pathname.path.endsWith(file_extension) }

            if (allsongs != null) {
                for (i in allsongs.indices) {
                    Log.d("all_songs", allsongs?.get(i)?.name)
                    val fis = activity?.openFileInput(allsongs?.get(i)?.name)
                    val br = BufferedReader(InputStreamReader(fis))

                    while ({s = br.readLine(); s}() != null) {
                        try {
                            if (s?.substring(s!!.lastIndexOf("."))?.trim()!!.isNotEmpty()) {
                                val songList = SongList()
                                val song = s!!.substring(s!!.lastIndexOf("\\") + 1, s!!.length - 4).trim()

                                songList.songTitle = song.substring(song.lastIndexOf(" - ") + 2).trim()
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

            for(i in 0..songsViewModelList.size)
            {
                songsViewModelList[i].sortWith(Comparator { obj1, obj2 ->
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


    internal class LoadSongsInBackGround(context: Context?) : AsyncTask<String, Void, List<List<SongList>>>()
    {
        lateinit var aDialog : AlertDialog
        lateinit var builder : AlertDialog.Builder
        lateinit var textView : TextView
        var context : Context? = context
        var dialogMsg : String = "Loading. Please wait..."
        var searchListLength: Int = 0

        override fun onPreExecute() {
            super.onPreExecute()
            textView = R.id.dialogMsg as TextView
            builder = AlertDialog.Builder(this.context!!)
            builder.setCancelable(false)
            builder.setView(R.layout.loading_dialog)
            textView.text = dialogMsg
            aDialog = builder.create()
            aDialog.show()
        }

        override fun doInBackground(vararg p0: String?): List<List<SongList>> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPostExecute(result: List<List<SongList>>) {
            if (aDialog.isShowing) {
                aDialog.dismiss()
            }
            //setupRecyclerView(recyclerView, result)
        }

    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}