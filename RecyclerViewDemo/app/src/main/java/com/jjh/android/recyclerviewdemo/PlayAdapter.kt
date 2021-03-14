package com.jjh.android.recyclerviewdemo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Provide binding from the plays data set to PlayViewHolders displayed
 * within a {@link RecyclerView}.
 */
class PlayAdapter : RecyclerView.Adapter<PlayViewHolder>() {

    companion object {
        private const val TAG = "PlayAdapter"
    }

    private val plays = PlaysDataSource()

    /**
     * Called when RecyclerView needs a new {@link PlayViewHolder} to represent
     * a new Play.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayViewHolder {
        Log.d(TAG, "onCreateViewHolder($viewType)")
        val inflater = LayoutInflater.from(parent.context)
        return PlayViewHolder(inflater, parent)
    }

    /**
     * Called by RecyclerView to display the PlayViewHolder at the specified position.
     */
    override fun onBindViewHolder(holder: PlayViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder($position)")
        val play: Play = plays.get(position)
        holder.bind(play)
    }

    /**
     *
     * Returns the total number of plays in the data set held by the adapter.
     */
    override fun getItemCount(): Int = plays.size

}

