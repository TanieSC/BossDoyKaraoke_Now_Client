package com.toks23.karaoke_now.ui.main

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.NonNull
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.toks23.karaoke_now.R
import com.toks23.karaoke_now.command.service.BackGroundTaskService
import com.toks23.karaoke_now.command.service.ConstantsService
import com.toks23.karaoke_now.model.SongList
import com.toks23.karaoke_now.searchFor
import com.toks23.karaoke_now.searchInTabPage
import com.toks23.karaoke_now.songCollections


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment(), SearchView.OnQueryTextListener, AdapterView.OnItemClickListener  {

    private var _handler = Handler()
    private val _constants = ConstantsService()
    private var _loadSongsByFilter : Int = -1
    private lateinit var _searchView: SearchView
    private lateinit var _rva: RecyclerViewSongsAdapter // = null
    private lateinit var _recyclerView : RecyclerView
    private lateinit var pageViewModel: PageViewModel

    override fun onQueryTextSubmit(query: String?): Boolean {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
       // if(TextUtils.isEmpty(query)){
            Log.d("SONGTITLE ", query)
        //}
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // loadQueryWithDelay(newText, 0)
        //if(_rva != null) {
           _rva.filter?.filter(newText)
       // }
        return true
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            loadSongs(arguments?.getInt(ARG_SECTION_NUMBER) ?: 0, songCollections)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        _loadSongsByFilter = _constants.SEARCH_SONGS_TITLE

        _searchView = activity?.findViewById(R.id.search_songs) as SearchView
        _searchView.setOnQueryTextListener(this)

        pageViewModel.index.observe(this, Observer{

            pageViewModel.songs.observe(this, Observer {data ->

                when (it) {
                    0 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    1 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    2 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    3 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    4 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    5 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    6 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    7 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    8 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    9 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    10 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    11 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    12 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    13 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    14 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    15 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    16 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    17 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    18 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    19 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    20 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    21 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    22 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    23 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    24 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    25 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                    26 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it], it)
                    }
                }
            })

        })

        return root
    }


    private fun filter(text: String, selectedTab : Int ){

        val filteredSongs: MutableList<SongList> = ArrayList()

        val songs : List<SongList> = songCollections[selectedTab]

        for (eachSong in songs) {
            if (eachSong.songTitle!!.toLowerCase().contains(text.toLowerCase()) || eachSong.songArtist!!.toLowerCase().contains(text.toLowerCase())) {
                filteredSongs.add(eachSong)
            }
        }


        Log.d("selectedTab ", selectedTab.toString())

    }

    private fun loadQueryWithDelay(query: String?, delay: Long) {

        _handler.removeCallbacks(delayedLoad)

        if (!TextUtils.isEmpty(query)) {
            searchFor = query
            _handler.postDelayed(delayedLoad, delay)
        }
    }

    private val delayedLoad = Runnable {

        when(_loadSongsByFilter){
            _constants.SEARCH_SONGS_TITLE -> {
                BackGroundTaskService(activity, _loadSongsByFilter).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            }
            _constants.SEARCH_SONGS_ARTIST -> {
                //BackGroundTaskService(activity, _loadSongsByFilter).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            }
            else -> songCollections
        }

    }

    private fun setupRecyclerView(@NonNull recyclerView: RecyclerView, data: List<SongList>, it: Int) {
        recyclerView.adapter = null
        recyclerView.setHasFixedSize(true)
        assert(recyclerView != null)
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        _rva = RecyclerViewSongsAdapter(context, data)
        recyclerView.adapter = _rva
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