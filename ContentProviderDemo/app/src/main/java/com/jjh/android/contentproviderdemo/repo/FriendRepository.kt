package com.jjh.android.contentproviderdemo.repo

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.jjh.android.contentproviderdemo.model.Friend
import com.jjh.android.contentproviderdemo.provider.FriendContentProvider

class FriendRepository(context: Context) {

    companion object {
        private const val TAG = "ContentProviderDemo"
    }

    private val myCR: ContentResolver

    init {
        myCR = context.contentResolver
    }

    fun addFriend(friend: Friend) {
        Log.d(TAG, "FriendRepository.addFriend($friend)")
        val values = ContentValues()
        values.put("id", friend.id)
        values.put("first_name", friend.firstName)
        values.put("last_name", friend.lastName)
        myCR.insert(FriendContentProvider.CONTENT_URI, values)
    }

    fun deleteFriend(id: Int): Boolean {
        Log.d(TAG, "FriendRepository.deleteFriend($id)")
        val selection = "id = \"$id\""
        val rowsDeleted = myCR.delete(FriendContentProvider.CONTENT_URI,
            selection, null)
        return rowsDeleted > 0
    }

    fun findFriend(id: Int): Friend? {
        Log.d(TAG, "FriendRepository.findFriend($id)")
        val projection = arrayOf("id", "first_name", "last_name")

        val selection = "id = \"$id\""

        val cursor = myCR.query(FriendContentProvider.CONTENT_URI,
            projection, selection, null, null)

        var friend: Friend? = null

        cursor?.apply {
            this.moveToFirst()
            val resultId = Integer.parseInt(this.getString(0))
            val firstName = this.getString(1)
            val lastName = this.getString(2)
            friend = Friend(resultId, firstName, lastName)
            this.close()
        }
        return friend
    }

}