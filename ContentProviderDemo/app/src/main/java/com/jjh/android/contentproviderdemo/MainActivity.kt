package com.jjh.android.contentproviderdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.jjh.android.contentproviderdemo.model.Friend
import com.jjh.android.contentproviderdemo.repo.FriendRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ContentProviderDemo"
    }

    private lateinit var repo: FriendRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "MainActivity.onCreate")
        repo = FriendRepository(this)
    }

    fun add(view: View) {
        Log.d(TAG, "MainActivity.add")
        val id = Integer.parseInt(idView.text.toString())
        val firstName = firstNameView.text.toString()
        val lastName = lastNameView.text.toString()
        val friend = Friend(id, firstName, lastName)
        repo.addFriend(friend)
    }

    fun delete(view: View) {
        Log.d(TAG, "MainActivity.delete")
        val id = Integer.parseInt(idView.text.toString())
        Log.d(TAG, "MainActivity.deleting($id)")
        val result = repo.deleteFriend(id)
        Log.d(TAG, "MainActivity.delete: $result")
    }

    fun find(view: View) {
        Log.d(TAG, "MainActivity.find")
        val id = Integer.parseInt(idView.text.toString())
        Log.d(TAG, "MainActivity.finding($id)")
        val friend = repo.findFriend(id)
        Log.d(TAG, "MainActivity.found($friend)")
        outputView.setText(friend.toString())
    }
}