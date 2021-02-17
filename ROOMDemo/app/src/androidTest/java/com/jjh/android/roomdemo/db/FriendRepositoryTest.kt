package com.jjh.android.roomdemo.db

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jjh.android.roomdemo.model.Friend
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import androidx.test.core.app.ActivityScenario
import com.jjh.android.roomdemo.MainActivity
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Ignore

@RunWith(AndroidJUnit4::class)
class FriendRepositoryTest {

    companion object {
        private const val TAG = "FriendRepositoryTest"
    }

    private val repository: FriendRepository

    init {
        val application = ApplicationProvider.getApplicationContext<Context>() as Application
        repository = FriendRepository(application, TestSchedulerProvider)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        repository.close()
    }

    @Test
    // @Ignore
    @Throws(Exception::class)
    fun insertNewFriendTest() {
        val friend = Friend(1, "John", "Hunt", 56)
        Log.d(TAG, "Setting up test Observer")
        val testObserver = TestObserver<Long>()
        repository.insertFriend(friend)
            .observeOn(Schedulers.trampoline())
            .subscribe(testObserver)
        Log.d(TAG, "checking test observer")
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val result = testObserver.values()[0]
        assertEquals(1, result)
    }


    @Test
    @Throws(Exception::class)
    fun findAllFriendsTest() {

        Log.d(TAG, "Setting up test Observer")
        val testObserver = TestObserver<List<Friend>>()
        repository.findAllFriends()
            .observeOn(Schedulers.trampoline())
            .subscribe(testObserver)
        Log.d(TAG, "checking test observer")
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val result = testObserver.values()
        Log.d(TAG, result.toString())
        val friend = result[0][0]
        assertTrue("checking id of friend", friend.id == 1)
        assertEquals("John", friend.firstName)
        assertEquals("Hunt", friend.lastName)
        assertEquals("checking age",56, friend.age)

    }

    @Test
    fun findFriendById() {

        Log.d(TAG, "Setting up test Observer")
        val testObserver = TestObserver<Friend>()

        repository.findFriendById(1)
            .observeOn(Schedulers.trampoline())
            .subscribe(testObserver)
        Log.d(TAG, "checking test observer")
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val result = testObserver.values()[0]
        assertTrue("checking id of friend", result?.id == 1)
        assertEquals("John", result?.firstName)
        assertEquals("Hunt", result?.lastName)
        assertEquals("checking age",56, result?.age)

    }

    @Test
    // @Ignore
    @Throws(Exception::class)
    fun deleteFriendTest() {
        val friend = Friend(1, "John", "Hunt", 56)
        Log.d(TAG, "Setting up test Observer")
        val testObserver = TestObserver<Int>()
        repository.deleteFriend(friend)
            .observeOn(Schedulers.trampoline())
            .subscribe(testObserver)
        Log.d(TAG, "checking test observer")
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val result = testObserver.values()[0]
        assertEquals(1, result)
    }

    @Test
    // @Ignore
    @Throws(Exception::class)
    fun deleteFriendByIdTest() {
        Log.d(TAG, "Setting up test Observer")
        val testObserver = TestObserver<Int>()
        repository.deleteFriendById(2)
            .observeOn(Schedulers.trampoline())
            .subscribe(testObserver)
        Log.d(TAG, "checking test observer")
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val result = testObserver.values()[0]
        assertEquals(1, result)
    }
}