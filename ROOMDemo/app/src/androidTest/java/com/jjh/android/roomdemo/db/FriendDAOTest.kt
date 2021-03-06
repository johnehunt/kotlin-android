package com.jjh.android.roomdemo.db

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jjh.android.roomdemo.model.Friend
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FriendDAOTest {

    private var db: FriendRoomDatabase?
    private var friendDao: FriendDao?

    init {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = FriendRoomDatabase.getDatabase(context)
        friendDao = db?.friendDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db?.close()
    }

    @Test
    @Ignore
    @Throws(Exception::class)
    fun writeFriendAndReadIn() {
        val friend = Friend(123, "John", "Hunt", 56)
        friendDao?.insert(friend)
        val foundFriend = friendDao?.findById(123)
        foundFriend?.apply {
            assertEquals(123, this.id)
            assertEquals("John", this.firstName)
            assertEquals("Hunt", this.lastName)
            assertEquals(56, this.age)
        }
    }

    @Test
    @Ignore
    @Throws(Exception::class)
    fun writeFriendAndDelete() {
        val friend = Friend(123, "John", "Hunt", 56)
        friendDao?.delete(friend)
        val foundFriend = friendDao?.findById(123)
        assertEquals(56, foundFriend)
    }


}