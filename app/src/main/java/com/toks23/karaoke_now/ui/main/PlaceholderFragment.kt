package com.toks23.karaoke_now.ui.main

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.toks23.karaoke_now.R
import com.toks23.karaoke_now.model.SongList
import com.toks23.karaoke_now.songCollections
import java.io.*

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment(), SearchView.OnQueryTextListener, AdapterView.OnItemClickListener  {

    private lateinit var _recyclerView : RecyclerView

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
            loadSongs(songCollections)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        pageViewModel.index.observe(this, Observer{
            pageViewModel.songs.observe(this, Observer {data ->
                when (it) {
                    0 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    1 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    2 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    3 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    4 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    5 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    6 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    7 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    8 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    9 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    10 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    11 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    12 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    13 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    14 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    15 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    16 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    17 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    18 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    19 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    20 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    21 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    22 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    23 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    24 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    25 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                    26 -> {
                        _recyclerView = root.findViewById(R.id.songList) as RecyclerView
                        setupRecyclerView(_recyclerView, data[it])
                    }
                }
            })

        })

        return root
    }

    private fun setupRecyclerView(@NonNull recyclerView: RecyclerView, data: ArrayList<SongList>) {
        recyclerView.adapter = null
        recyclerView.setHasFixedSize(true)
        assert(recyclerView != null)
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        var rva = RecyclerViewSongsAdapter(context,  data)
        recyclerView.adapter = rva
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