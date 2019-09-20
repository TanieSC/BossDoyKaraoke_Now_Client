package com.toks23.karaoke_now

import androidx.recyclerview.widget.DiffUtil
import com.toks23.karaoke_now.model.SongList

class DiffUtilCallback(private val oldList: ArrayList<MutableList<SongList>>, private val newList: ArrayList<MutableList<SongList>>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].forEach { i -> i.songId } == newList[newItemPosition].forEach{ i -> i.songId}

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}