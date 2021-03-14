package com.jjh.android.recyclerviewdemo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * View used to display information about a play.
 * Note attachToRoot indicates true - attached to parent view now otherwise doing it later
 * when the holder is actually added to the parent view
 */
class PlayViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.play_list_item,
                            parent, false)) {

    companion object {
        private const val TAG = "PlayViewHolder"
    }

    private val titleView = itemView.findViewById<TextView>(R.id.play_title)
    private val yearView = itemView.findViewById<TextView>(R.id.play_year)


    fun bind(play: Play) {
        Log.d(TAG, "bind($play)")
        titleView.text = play.title
        yearView.text = play.year.toString()
    }

}