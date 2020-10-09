package ca.qc.cgodin.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.util.*

class StudentBDD(context: Context?) {
    companion object {
        private const val VERSION = 1
        private const val NOM_BDD = "student.db"
        private const val TABLE_STUDENTS = "table_students"
        private const val COL_ID = "ID"
        private const val NUM_COL_ID = 0
        private const val COL_FIRSTNAME = "FIRSTNAME"
        private const val NUM_COL_FIRSTNAME = 1
        private const val COL_LASTNAME = "LASTNAME"
        private const val NUM_COL_LASTNAME = 2
        private const val COL_EMAIL = "EMAIL"
        private const val NUM_COL_EMAIL = 3
        private const val COL_PHONENUMBER = "PHONENUMBER"
        private const val NUM_COL_PHONENUMBER = 4
        private const val COL_USERNAME = "USERNAME"
        private const val NUM_COL_USERNAME = 5
        private const val COL_PASSWORD = "PASSWORD"
        private const val NUM_COL_PASSWORD = 6
    }
    private val students: StudentBaseSQLite
    private lateinit var bdd: SQLiteDatabase

    init {
        students = StudentBaseSQLite(context, NOM_BDD, null, VERSION)
    }

    fun openForWrite() {
        bdd = students.writableDatabase
    }

    fun openForRead() {
        bdd = students.readableDatabase
    }

    fun close() {
        bdd.close()
    }

    fun insertStudent(student: Student): Long {
        val content = ContentValues()
        content.put(COL_FIRSTNAME, student.firstName)
        content.put(COL_LASTNAME, student.lastName)
        content.put(COL_EMAIL, student.email)
        content.put(COL_PHONENUMBER, student.phoneNumber)
        content.put(COL_USERNAME, student.username)
        content.put(COL_PASSWORD, student.password)
        return bdd.insert(TABLE_STUDENTS, null, content)
    }

    fun updateStudent(id: Int, student: Student): Int {
        val content = ContentValues()
        content.put(COL_FIRSTNAME, student.firstName)
        content.put(COL_LASTNAME, student.lastName)
        content.put(COL_EMAIL, student.email)
        content.put(COL_PHONENUMBER, student.phoneNumber)
        content.put(COL_USERNAME, student.username)
        content.put(COL_PASSWORD, student.password)
        return bdd.update(TABLE_STUDENTS, content, COL_ID + " = " + id, null)
    }

    fun removeStudent(username: String): Int {
        return bdd.delete(TABLE_STUDENTS, COL_USERNAME + " = " + username, null)
    }

    fun getStudent(username: String): Student? {
        val c = bdd.query(TABLE_STUDENTS, arrayOf(COL_ID, COL_FIRSTNAME, COL_LASTNAME, COL_EMAIL, COL_PHONENUMBER, COL_USERNAME,
                COL_PASSWORD), COL_USERNAME + " LIKE \"" + username + "\"", null, null,
                null, COL_USERNAME)
        return cursorToStudent(c)
    }

    fun cursorToStudent(c: Cursor): Student? {
        if (c.count == 0) {
            c.close()
            return null
        }
        val student = Student(c.getInt(NUM_COL_ID),
                c.getString(NUM_COL_FIRSTNAME),
                c.getString(NUM_COL_LASTNAME),
                c.getString(NUM_COL_EMAIL),
                c.getString(NUM_COL_PHONENUMBER),
                c.getString(NUM_COL_USERNAME),
                c.getString(NUM_COL_PASSWORD))
        c.close()
        return student
    }

    val allStudents: ArrayList<Student>?
        get() {
            val c = bdd.query(TABLE_STUDENTS, arrayOf(COL_ID, COL_FIRSTNAME, COL_LASTNAME, COL_EMAIL, COL_PHONENUMBER, COL_USERNAME,
                    COL_PASSWORD), null, null, null, null, COL_FIRSTNAME)
            if (c.count == 0) {
                c.close()
                return null
            }
            val studentList = ArrayList<Student>()
            while (c.moveToNext()) {
                val student = Student(c.getInt(NUM_COL_ID),
                        c.getString(NUM_COL_FIRSTNAME),
                        c.getString(NUM_COL_LASTNAME),
                        c.getString(NUM_COL_EMAIL),
                        c.getString(NUM_COL_PHONENUMBER),
                        c.getString(NUM_COL_USERNAME),
                        c.getString(NUM_COL_PASSWORD)
                )
                studentList.add(student)
            }
            c.close()
            return studentList
        }
}