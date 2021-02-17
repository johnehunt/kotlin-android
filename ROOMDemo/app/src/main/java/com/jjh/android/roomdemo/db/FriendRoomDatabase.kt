package com.jjh.android.roomdemo.db

import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Database
import com.jjh.android.roomdemo.model.Friend

@Database(entities = [Friend::class], version = 1)
abstract class FriendRoomDatabase : RoomDatabase() {

    abstract fun friendDao(): FriendDao

    companion object {

        private var _SINGLETON: FriendRoomDatabase? = null

        internal fun getDatabase(context: Context): FriendRoomDatabase {
            if (_SINGLETON == null) {
                synchronized(FriendRoomDatabase::class.java) {
                    if (_SINGLETON == null) {
                        _SINGLETON = Room.databaseBuilder(
                            context.applicationContext,
                            FriendRoomDatabase::class.java,
                            "friends_db"
                        ).build()
                    }
                }
            }
            return _SINGLETON!!
        }
    }
}