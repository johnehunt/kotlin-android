package com.jjh.android.recyclerviewdemo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

data class Play(val title: String, val year: Int)

/**
 * Provide binding from the plays data set to PlayViewHolders displayed
 * within a {@link RecyclerView}.
 */
class PlayAdapter : RecyclerView.Adapter<PlayViewHolder>() {

    companion object {
        private const val TAG = "PlayAdapter"
    }

    private val plays: List<Play> = listOf(
        Play("The Tempest", 1610),
        Play("The Comedy of Errors", 1598),
        Play("Much Ado About Nothing", 1599),
        Play("Hamlet", 1600),
        Play("Henry IV Part 1", 1597),
        Play("Henry IV Part 2", 1598),
        Play("Richard III", 1595),
        Play("King Lear", 1606),
        Play("Twelfth Night", 1601),
        Play("A midsummer Night's Dream", 15966),
        Play("Romeo and Juliet", 1594)
    )

    /**
     * Called when RecyclerView needs a new {@link PlayViewHolder} to represent
     * a new Play.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayViewHolder {
        Log.d(TAG, "onCreateViewHolder()")
        val inflater = LayoutInflater.from(parent.context)
        return PlayViewHolder(inflater, parent)
    }

    /**
     * Called by RecyclerView to display the playviewholder at the specified position.
     */
    override fun onBindViewHolder(holder: PlayViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder($position)")
        val play: Play = plays[position]
        holder.bind(play)
    }

    /**
     *
     * Returns the total number of plays in the data set held by the adapter.
     */
    override fun getItemCount(): Int = plays.size

}

