package com.jjh.android.recyclerviewdemo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * View used to display information about a play
 */
class PlayViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.play_list_item, parent, false)) {

    companion object {
        private const val TAG = "PlayViewHolder"
    }

    private var titleView: TextView? = null
    private var yearView: TextView? = null


    init {
        titleView = itemView.findViewById(R.id.play_title)
        yearView = itemView.findViewById(R.id.play_year)
    }

    fun bind(play: Play) {
        Log.d(TAG, "bind($play)")
        titleView!!.text = play.title
        yearView!!.text = play.year.toString()
    }

}