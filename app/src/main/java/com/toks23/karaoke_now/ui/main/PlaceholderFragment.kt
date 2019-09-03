package com.toks23.karaoke_now.ui.main

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.toks23.karaoke_now.R
import com.toks23.karaoke_now.model.SongList
import kotlinx.android.synthetic.main.content_song_listitem.view.*
import java.io.*
import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment(), SearchView.OnQueryTextListener, AdapterView.OnItemClickListener  {

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
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
       // val textView: TextView = root.findViewById(R.id.section_label)
        //pageViewModel.text.observe(this, Observer<String> {
        //    textView.text = it
        //})

        val recyclerView : RecyclerView = root.findViewById(R.id.songList)
        //pageViewModel.type.observe(this, Observer<String> {

       // })

        LoadSongsInBackGround(activity!!.applicationContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

        return root
    }

    private fun generateSongList(file_extension: String): ArrayList<ArrayList<SongList>>? {
        val songsViewModelList : ArrayList<SongList> = ArrayList()

        try {
            var s: String
            val allsongs = activity?.filesDir?.listFiles { pathname -> pathname.path.endsWith(file_extension) }

            if (allsongs != null) {
                for (i in allsongs.indices) {
                    Log.d("all_songs", allsongs?.get(i)?.name)
                    val fis = activity?.openFileInput(allsongs?.get(i)?.name)
                    val br = BufferedReader(InputStreamReader(fis))

                    while ((s = br.readLine()) != null) {
                        try {
                            // String file_extn = s.substring(s.lastIndexOf(".")).trim();
                            if (!s.substring(s.lastIndexOf(".")).trim { it <= ' ' }.isEmpty()) {
                                val songList = SongList()
                                val song = s.substring(s.lastIndexOf("\\") + 1, s.length - 4)
                                    .trim { it <= ' ' }

                                songList.songTitle = (song.substring(song.lastIndexOf(" - ") + 2).trim { it <= ' ' })
                                songList.songArtist = song
                                songList.songFilename = s
                                songList.songFilePath = s

                                songsViewModelList.add(songList)

                                // Log.d("all_songs", song);
                            }

                        } catch (iode: StringIndexOutOfBoundsException) {

                        }

                    }
                    br.close()
                }
            }


            songsViewModelList.sortWith(Comparator { obj1, obj2 ->
                // ## Ascending order
                obj1.songTitle.compareTo(obj2.songArtist, true)
            })


            return songsViewModelList
        } catch (e: IOException) {
            e.printStackTrace()
            Log.i("Data :", "FILE - false")
            return null
        }

    }


    internal class LoadSongsInBackGround(context: Context) : AsyncTask<String, Void, List<List<SongList>>>()
    {
        lateinit var aDialog : AlertDialog
        lateinit var builder : AlertDialog.Builder
        lateinit var textView : TextView
        var context : Context = context
        var dialogMsg : String = "Loading. Please wait..."
        var searchListLength: Int = 0

        override fun onPreExecute() {
            super.onPreExecute()
            textView = R.id.dialogMsg as TextView
            builder = AlertDialog.Builder(this.context)
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

    private fun loadSongsInBackGround(loadSongs: String) {

        object : AsyncTask<String, Void, List<SongList>>() {

            lateinit var aDialog : AlertDialog
            lateinit var builder : AlertDialog.Builder
            lateinit var textView : TextView
            var dialogMsg : String = "Loading. Please wait..."
            var searchListLength: Int = 0

            override fun onPreExecute() {
                super.onPreExecute()
                textView = R.id.dialogMsg as TextView
                builder = AlertDialog.Builder(activity!!.applicationContext)
                builder.setCancelable(false)
                builder.setView(R.layout.loading_dialog)
                textView.text = dialogMsg
                aDialog = builder.create()
                aDialog.show()
            }

            override fun doInBackground(vararg p0: String?): List<SongList> {

                val mainSongList : List<SongList> = ArrayList()

                return mainSongList
            }

            override fun onPostExecute(result: List<SongList>) {
                if (aDialog.isShowing) {
                    aDialog.dismiss()
                }
                //setupRecyclerView(recyclerView, result)
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
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