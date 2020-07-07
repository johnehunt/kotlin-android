package com.jjh.android.samplecontentprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper internal constructor(context: Context?) :
    SQLiteOpenHelper(context, "friendsDB", null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "friends"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table friends ("
                    + " _id integer PRIMARY KEY autoincrement, name  text, "
                    + " age integer );  "
        )
    }

    override fun onUpgrade(db: SQLiteDatabase,
                           oldVersion: Int,
                           newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}