package com.jjh.android.roomdemo.db

import androidx.room.*
import com.jjh.android.roomdemo.model.Friend

@Dao
interface FriendDao {

    @Insert
    fun insert(friend: Friend): Long

    @Insert
    fun insertAll(vararg friends: Friend): List<Long>

    @Update
    fun update(friend: Friend): Int

    @Query("SELECT * FROM friends")
    fun findAll(): List<Friend>

    @Query("SELECT * FROM friends WHERE id=:id")
    fun findById(id: Int): Friend

    @Query("SELECT * FROM friends WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Friend

    @Delete
    fun delete(friend: Friend): Int

    @Delete
    fun deleteAll(vararg friends: Friend): Int

}

// return row ids