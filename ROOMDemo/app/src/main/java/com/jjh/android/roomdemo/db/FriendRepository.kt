package com.jjh.android.roomdemo.db

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.jjh.android.roomdemo.model.Friend

class FriendRepository(private val application: Application) {

    val searchResults = MutableLiveData<List<Friend>>()

    private var friendDao: FriendDao? =
        FriendRoomDatabase.getDatabase(application)?.friendDao()

    fun close() {
        FriendRoomDatabase.getDatabase(application)?.close()
    }

    fun insertFriend(friend: Friend) {
        val task = InsertAsyncTask(friendDao)
        task.execute(friend)
    }

    fun findAllFriends() {
        val task = QueryAsyncTask(friendDao)
        task.delegate = this
        task.execute()
    }

    fun deleteFriend(friend: Friend) {
        val task = DeleteAsyncTask(friendDao)
        task.execute(friend)
    }

    // Delegate Callback member function
    fun asyncFinished(results: List<Friend>) {
        searchResults.value = results
    }

    // Handle insertions in the background
    private class InsertAsyncTask(private val friendDao: FriendDao?): AsyncTask<Friend, Void, Void>() {
        override fun doInBackground(vararg params: Friend): Void? {
            friendDao?.insert(params[0])
            return null
        }
    }

    // Handle query tasks
    private class QueryAsyncTask (val friendDao: FriendDao?): AsyncTask<String, Void, List<Friend>>() {
        lateinit var delegate: FriendRepository

        override fun doInBackground(vararg params: String?): List<Friend>? {
            return friendDao?.findAll()
        }

        override fun onPostExecute(result: List<Friend>) {
            super.onPostExecute(result)
            delegate.asyncFinished(result)
        }
    }

    // Handle deletions in the background
    private class DeleteAsyncTask (private val friendDao: FriendDao?): AsyncTask<Friend, Void, Void>() {
        override fun doInBackground(vararg params: Friend): Void? {
            friendDao?.delete(params[0])
            return null
        }
    }

}