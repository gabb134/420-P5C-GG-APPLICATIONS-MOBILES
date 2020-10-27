package ca.qc.cgodin.roomstudent

import androidx.lifecycle.LiveData
import androidx.room.*



    @Entity(tableName = "student_table")
    data class Student(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        val id:Int,
        @ColumnInfo(name = "firstName")
        val firstName: String,
        @ColumnInfo(name = "lastName")
        val lastName: String,
        @ColumnInfo(name = "phoneNumber")
        val phoneNumber: String,
        @ColumnInfo(name = "email")
        val email: String )


