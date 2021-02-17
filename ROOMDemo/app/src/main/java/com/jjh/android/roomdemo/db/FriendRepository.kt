package com.jjh.android.roomdemo.db

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jjh.android.roomdemo.model.Friend
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable

class FriendRepository(private val application: Application,
                       private val schedulerProvider: SchedulerProvider = DefaultSchedulerProvider()) {

    companion object {
        private const val TAG = "FriendRepository"
    }

    val searchResults = MutableLiveData<List<Friend>>()

    private var friendDao: FriendDao =
        FriendRoomDatabase.getDatabase(application)?.friendDao()!!

    fun close() {
        FriendRoomDatabase.getDatabase(application)?.close()
    }

    fun insertFriend(friend: Friend): Observable<Long> {
        return Observable.create<Long> {
            val result = friendDao.insert(friend)
            it.onNext(result)
            it.onComplete()
        }.subscribeOn(schedulerProvider.newThread())
    }

    fun findAllFriends(): Observable<List<Friend>> {
        return Observable.create<List<Friend>> {
            val results = friendDao.findAll()
            it.onNext(results)
            it.onComplete()
        }.subscribeOn(schedulerProvider.newThread())
            .doOnNext { Log.d(TAG, "findAllFriendsObservable next: $it") }
            .doOnError { Log.d(TAG, "findAllFriendsObservable error $it") }
            .doOnComplete { Log.d(TAG, "findAllFriendsObservable Completed") }
    }

    fun findFriendById(id: Int): Observable<Friend> {
        return Observable.create<Friend> {
            Log.d(TAG, "findFriendById($id) - starting Observable")
            val friend = friendDao.findById(id)
            Log.d(TAG, "findFriendById($id) - search completed emitting data")
            it.onNext(friend)
            it.onComplete()
        }.subscribeOn(schedulerProvider.newThread())
    }

    fun deleteFriend(friend: Friend): Observable<Int> {
        return Observable.create<Int> {
            val result = friendDao.delete(friend)
            it.onNext(result)
            it.onComplete()
        }.subscribeOn(schedulerProvider.newThread())
    }

}