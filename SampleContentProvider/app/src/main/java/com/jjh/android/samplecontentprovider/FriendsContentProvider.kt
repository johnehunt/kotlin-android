package com.jjh.android.samplecontentprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

class FriendsContentProvider : ContentProvider() {
    private var dbHelper: DatabaseHelper? = null

    companion object {
        private const val TABLE_NAME = "friends"
        const val AUTHORITY = "com.jjh.android.provider.FriendsContentProvider"
        private val CONTENT_URI: Uri =
            Uri.parse("content://$AUTHORITY/friends")
        private const val CONTENT_TYPE =
            "vnd.android.cursor.dir/vnd.com.jjh.android.provider.friends"
        private var sUriMatcher: UriMatcher? = null
        private const val FRIENDS = 1

        init {
            sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            sUriMatcher!!.addURI(
                AUTHORITY,
                TABLE_NAME,
                FRIENDS
            )
        }
    }

    override fun onCreate(): Boolean {
        dbHelper = DatabaseHelper(context)
        return true
    }

    override fun delete(
        uri: Uri,
        where: String?,
        whereArgs: Array<String>?): Int {
        val db = dbHelper!!.writableDatabase
        val count: Int
        count = when (sUriMatcher!!.match(uri)) {
            FRIENDS -> db.delete(TABLE_NAME, where, whereArgs)
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String? {
        return when (sUriMatcher!!.match(uri)) {
            FRIENDS -> CONTENT_TYPE
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
    }

    override fun insert(
        uri: Uri,
        initialValues: ContentValues?): Uri? {
        require(sUriMatcher!!.match(uri) == FRIENDS) { "Unknown URI $uri" }
        val values: ContentValues
        values = initialValues?.let { ContentValues(it) } ?: ContentValues()
        val db = dbHelper!!.writableDatabase
        val rowId =
            db.insert(TABLE_NAME, "name", values)
        if (rowId > 0) {
            val noteUri = ContentUris.withAppendedId(
                CONTENT_URI, rowId
            )
            context!!.contentResolver.notifyChange(noteUri, null)
            return noteUri
        }
        throw SQLException("Failed to insert row into $uri")
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val qb = SQLiteQueryBuilder()
        when (sUriMatcher!!.match(uri)) {
            FRIENDS -> qb.tables = TABLE_NAME
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        val db = dbHelper!!.readableDatabase
        val c = qb.query(
            db, projection, selection, selectionArgs, null,
            null, sortOrder
        )
        c.setNotificationUri(context!!.contentResolver, uri)
        return c
    }

    override fun update(
        uri: Uri, values: ContentValues?, where: String?,
        whereArgs: Array<String>?): Int {
        val db = dbHelper!!.writableDatabase
        val count: Int
        count = when (sUriMatcher!!.match(uri)) {
            FRIENDS -> db.update(TABLE_NAME, values, where, whereArgs)
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }
}