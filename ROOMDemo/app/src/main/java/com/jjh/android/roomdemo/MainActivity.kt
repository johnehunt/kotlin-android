package com.jjh.android.roomdemo

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jjh.android.roomdemo.db.DefaultSchedulerProvider
import com.jjh.android.roomdemo.db.FriendRepository
import com.jjh.android.roomdemo.model.Friend
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var repository: FriendRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository = FriendRepository(application)
        updateFriendsList()
    }

    fun doAddFriendButtonClick(v: View) {
        val friend = Friend(
            idEditText.text.toString().toInt(),
            firstNameEditText.text.toString(),
            lastNameEditText.text.toString(),
            ageEditText.text.toString().toInt())
        repository.insertFriend(friend)
            .observeOn(DefaultSchedulerProvider.ui())
            .subscribe { updateFriendsList() }
    }

    private fun updateFriendsList() {
        repository.findAllFriends()
            .observeOn(DefaultSchedulerProvider.ui())
            .subscribe {
                val list = it.map{it.toString()}.toList()
                val arrayAdapter:ArrayAdapter<String> =
                    ArrayAdapter<String>(
                        this,
                        R.layout.listview_layout,
                        R.id.textView,
                        list)
                // Update the list view with friends
                listView.adapter = arrayAdapter
                // Update table rows
                (idEditText as TextView).text = ""
                (firstNameEditText as TextView).text = ""
                (lastNameEditText as TextView).text = ""
                (ageEditText as TextView).text = ""
            }
    }

}