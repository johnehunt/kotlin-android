package com.jjh.android.roomdemo.db

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jjh.android.roomdemo.model.Friend
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import androidx.test.core.app.ActivityScenario
import com.jjh.android.roomdemo.MainActivity
import org.junit.Ignore

@RunWith(AndroidJUnit4::class)
class FriendRepositoryTest {

    private lateinit var repository: FriendRepository

    init {
        val application = ApplicationProvider.getApplicationContext<Context>() as Application
        repository = FriendRepository(application)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        repository.close()
    }

    @Test
    @Ignore
    @Throws(Exception::class)
    fun persistFriendTest() {
        ActivityScenario.launch(MainActivity::class.java).onActivity { activity ->
            val friend = Friend(2, "John", "Hunt", 55)
            repository.insertFriend(friend)
            repository.findAllFriends()
            repository.searchResults.observe(activity, Observer {
                value -> assertTrue("returned list contains added friend", value.contains(friend))
            })
        }
    }

    @Test
    @Throws(Exception::class)
    fun findAllFriendsTest() {
        ActivityScenario.launch(MainActivity::class.java).onActivity { activity ->
            repository.findAllFriends()
            repository.searchResults.observe(activity, Observer {
                    value -> assertTrue("returned list is non null", value.isEmpty())
            })
        }
    }

    @Test
    @Ignore
    @Throws(Exception::class)
    fun deleteFriendTest() {
        val friend = Friend(123, "John", "Hunt", 55)
        repository.deleteFriend(friend)
    }
}