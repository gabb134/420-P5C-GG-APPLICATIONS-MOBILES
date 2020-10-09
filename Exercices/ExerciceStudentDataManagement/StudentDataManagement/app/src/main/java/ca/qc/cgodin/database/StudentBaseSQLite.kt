package ca.qc.cgodin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class StudentBaseSQLite(context: Context?, name: String, factory: CursorFactory?, version: Int)
    :SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_BDD)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE " + TABLE_STUDENT)
        onCreate(db)
    }

    companion object {
        private const val TABLE_STUDENT = "table_students"
        private const val COL_ID = "ID"
        private const val COL_FIRSTNAME = "FIRSTNAME"
        private const val COL_LASTNAME = "LASTNAME"
        private const val COL_EMAIL = "EMAIL"
        private const val COL_PHONENUMBER = "PHONENUMBER"
        private const val COL_USERNAME = "USERNAME"
        private const val COL_PASSWORD = "PASSWORD"
        private const val CREATE_BDD = "CREATE TABLE " + TABLE_STUDENT + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FIRSTNAME + " TEXT NOT NULL, " +
                COL_LASTNAME + " TEXT NOT NULL, " +
                COL_EMAIL + " TEXT NOT NULL, " +
                COL_PHONENUMBER + " TEXT NOT NULL, " +
                COL_USERNAME + " TEXT NOT NULL, " +
                COL_PASSWORD + " TEXT NOT NULL);"
    }
}