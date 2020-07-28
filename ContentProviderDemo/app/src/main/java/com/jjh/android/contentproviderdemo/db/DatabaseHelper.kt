package com.jjh.android.contentproviderdemo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper internal constructor(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "friendsDB"
        private const val TAG = "ContentProviderDemo"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "friends"
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "DatabaseHelper.onCreate")
        db.execSQL("create table $TABLE_NAME ("
                    + " id integer PRIMARY KEY autoincrement, first_name  text, "
                    + " last_name text );  "
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "DatabaseHelper.onUpgrade")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}